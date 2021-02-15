package fr.tse.fise3.architecture_ntiers.Projet_stage;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.MobilityDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.UserDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Mobility;
import fr.tse.fise3.architecture_ntiers.Projet_stage.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class MobilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MobilityDao mobilityDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void getAllByDateTest() throws Exception {
        LocalDate date = LocalDate.of(2049, 11, 3);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/mobilities?date=" + date.format(DateTimeFormatter.ISO_DATE))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getAllByMultipleCriteriaTest() throws Exception {
        LocalDate date = LocalDate.of(2049, 11, 3);
        String country = "France";
        String firstname = "Oui";
        String lastname = "Non";
        String email = "oui.non@telecom-st-etienne.fr";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/mobilities?"
                        + "country=" + country
                        + "&firstname=" + firstname
                        + "&lastname=" + lastname
                        + "&email=" + email
                        + "&date=" + date.format(DateTimeFormatter.ISO_DATE)
                        + "&typeUser=" + Constants.TYPE_USER_FISE2
                )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void postAndDeleteTest() throws Exception {
        int numberOfMobilities = mobilityDao.findAllByCriteria(new HashMap<>()).size();
        Map<String, String> bodyPost = new HashMap<>();
        bodyPost.put("country", "Espagne");
        bodyPost.put("city", "Madrid");
        bodyPost.put("beginDate", LocalDate.of(2025, 1, 1).format(DateTimeFormatter.ISO_DATE));
        bodyPost.put("endDate", LocalDate.of(2025, 6, 15).format(DateTimeFormatter.ISO_DATE));
        bodyPost.put("idUser", userDao.findUserByEmail("oui.non@telecom-st-etienne.fr").getId().toString());
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                .post("/mobilities")
                .content(asJsonString(bodyPost))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
        ;
        assertEquals(numberOfMobilities + 1, mobilityDao.findAllByCriteria(new HashMap<>()).size());
        String idNewMobilityString = response.getResponse().getContentAsString();
        Long idNewMobility = Long.parseLong(idNewMobilityString);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/mobilities/" + idNewMobility)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(true)))
        ;
        System.out.println(mobilityDao.findAllByCriteria(new HashMap<>()));
        assertEquals(numberOfMobilities, mobilityDao.findAllByCriteria(new HashMap<>()).size());
    }

    @Test
    public void patchMobilityTest() throws Exception {

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("email", "oui.non@telecom-st-etienne.fr");
        Mobility mobilityToUpdate = mobilityDao.findAllByCriteria(criteria).get(0);

        Long idMobility = mobilityToUpdate.getId();
        LocalDate beginDateBeforeUpdate = mobilityToUpdate.getBeginDate();
        String countryBeforeUpdate = mobilityToUpdate.getCountry();
        String cityBeforeUpdate = mobilityToUpdate.getCity();

        String beginDateToUpdate = LocalDate.of(2048, 11, 3).format(DateTimeFormatter.ISO_DATE);
        String countryToUpdate = "Etats-Unis";
        String cityToUpdate = "New York";
        Map<String, Object> patchBody = new HashMap<>();
        patchBody.put("country", countryToUpdate);
        patchBody.put("city", cityToUpdate);
        patchBody.put("beginDate", beginDateToUpdate);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/mobilities/" + idMobility)
                .content(asJsonString(patchBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.country", is(countryToUpdate)))
                .andExpect(jsonPath("$.city", is(cityToUpdate)))
                .andExpect(jsonPath("$.beginDate", is(beginDateToUpdate)))
        ;

        assertEquals(countryToUpdate, mobilityDao.findById(idMobility).getCountry());
        Map<String, Object> patchBodyRevert = new HashMap<>();
        patchBodyRevert.put("country", countryBeforeUpdate);
        patchBodyRevert.put("city", cityBeforeUpdate);
        patchBodyRevert.put("beginDate", beginDateBeforeUpdate.format(DateTimeFormatter.ISO_DATE));


        mockMvc.perform(MockMvcRequestBuilders
                .patch("/mobilities/" + idMobility)
                .content(asJsonString(patchBodyRevert))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.country", is(countryBeforeUpdate)))
                .andExpect(jsonPath("$.city", is(cityBeforeUpdate)))
                .andExpect(jsonPath("$.beginDate", is(beginDateBeforeUpdate.format(DateTimeFormatter.ISO_DATE))))
        ;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
