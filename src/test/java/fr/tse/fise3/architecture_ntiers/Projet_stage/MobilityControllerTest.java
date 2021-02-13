package fr.tse.fise3.architecture_ntiers.Projet_stage;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Mobility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class MobilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
                        + "&date=" + date.format(DateTimeFormatter.ISO_DATE))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
