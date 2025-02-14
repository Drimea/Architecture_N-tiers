package fr.tse.fise3.architecture_ntiers.Projet_stage.controller;

import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.MobilityDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.UserDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Mobility;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class MobilityController {

    @Autowired
    MobilityDao mobilityDao;

    @Autowired
    UserDao userDao;

    @GetMapping(path = "/mobilities")
    public List<Mobility> getAllByCriteria(@RequestParam(required = false) String country,
                                           @RequestParam(required = false) String firstname,
                                           @RequestParam(required = false) String lastname,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                           @RequestParam(required = false) String typeUser) {
        Map<String, Object> criteria = new HashMap<>();
        if (country != null) {
            criteria.put("country", country);
        }
        if (firstname != null) {
            criteria.put("firstname", firstname);
        }
        if (lastname != null) {
            criteria.put("lastname", lastname);
        }
        if (email != null) {
            criteria.put("email", email);
        }
        if (date != null) {
            criteria.put("date", date);
        }
        if (typeUser != null) {
            criteria.put("typeUser", typeUser);
        }
        return mobilityDao.findAllByCriteria(criteria);
    }

    @PostMapping(path = "/mobilities")
    @ResponseStatus(HttpStatus.CREATED)
    public Long postMobility(@RequestBody Map<String, String> bodyPost) {
        String country = bodyPost.get("country");
        String city = bodyPost.get("city");
        LocalDate beginDate = LocalDate.parse(bodyPost.get("beginDate"));
        LocalDate endDate = LocalDate.parse(bodyPost.get("endDate"));
        User user = userDao.findUserById(Long.parseLong(bodyPost.get("idUser")));
        Mobility mobility = mobilityDao.create(user, country, city, beginDate, endDate);
        return mobility.getId();
    }

    @PatchMapping(path="/mobilities/{mobilityId}")
    public Mobility patchMobility(@PathVariable Long mobilityId, @RequestBody Map<String, Object> bodyPatch) {
        Mobility mobility = mobilityDao.findById(mobilityId);
        if (mobility == null){
            return null;
        }
        if (bodyPatch.containsKey("country")) {
            mobility.setCountry(bodyPatch.get("country").toString());
        }
        if (bodyPatch.containsKey("city")) {
            mobility.setCity(bodyPatch.get("city").toString());
        }
        if (bodyPatch.containsKey("beginDate")) {
            mobility.setBeginDate(LocalDate.parse(bodyPatch.get("beginDate").toString(), DateTimeFormatter.ISO_DATE));
        }
        if (bodyPatch.containsKey("endDate")) {
            mobility.setEndDate(LocalDate.parse(bodyPatch.get("endDate").toString(), DateTimeFormatter.ISO_DATE));
        }
        return mobilityDao.update(mobility);
    }

    @DeleteMapping(path="/mobilities/{mobilityId}")
    public boolean deleteMobility(@PathVariable Long mobilityId) {
        Mobility mobility = mobilityDao.findById(mobilityId);
        if (mobility==null) {
            return false;
        }
        mobilityDao.delete(mobility);
        return true;
    }
}
