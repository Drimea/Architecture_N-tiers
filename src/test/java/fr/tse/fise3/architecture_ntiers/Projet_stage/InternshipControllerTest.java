package fr.tse.fise3.architecture_ntiers.Projet_stage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class InternshipControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getInternshipsOfCountryTest() throws Exception{
        String country = "France";
        mockMvc.perform(MockMvcRequestBuilders
               .get("/internshipsByCountry?country=" + country)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getInternshipsOfDateTest() throws Exception{
        LocalDate date = LocalDate.of(2049, 11, 3);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/internshipsByDate?date=" + date.format(DateTimeFormatter.ISO_DATE))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getInternshipsOfEmailTest() throws Exception{
        String email = "oui.non@telecom-st-etienne.fr";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/internshipsByEmail?email=" + email)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getInternshipsOfNameTest() throws Exception{
        String firstname = "Oui";
        String lastname = "Non";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/internshipsByName?firstname=" + firstname+"&lastname=" + lastname)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
