package fr.tse.fise3.architecture_ntiers.Projet_stage.controller;

import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.InternshipDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@RestController
public class InternshipController {

    @Autowired
    InternshipDao internshipDao;

    @GetMapping(path = "/internshipsByCountry")
    public Collection<Internship> getInternshipsOfCountry(
            @RequestParam(name = "country") String country) {
        return internshipDao.FindAllByCountry(country);
    }

    @GetMapping(path = "/internshipsByDate")
    public Collection<Internship> getInternshipsOfDate(
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return internshipDao.FindAllByDate(date);
    }

    @GetMapping(path = "/internshipsByEmail")
    public Collection<Internship> getInternshipsOfEmail(
            @RequestParam(name = "email") String email) {
        return internshipDao.FindAllByEmail(email);
    }

    @GetMapping(path = "/internshipsByName")
    public Collection<Internship> getInternshipsOfName(
            @RequestParam(name = "firstname") String firstname,
            @RequestParam(name = "lastname") String lastname) {
        return internshipDao.FindAllByFirstnameAndLastname(firstname, lastname);
    }
}
