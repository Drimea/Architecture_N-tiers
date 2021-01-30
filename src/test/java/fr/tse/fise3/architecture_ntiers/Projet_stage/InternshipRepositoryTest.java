package fr.tse.fise3.architecture_ntiers.Projet_stage;

import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.InternshipDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.Instant;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class InternshipRepositoryTest {

    @Autowired
    private InternshipDao internshipDao;

    @Test
    public void FindAllByCountryTest() {
        List<Internship> internships = internshipDao.FindAllByCountry("France");
        assertEquals(1, internships.size());
        assertEquals("France", internships.get(0).getEnterprise().getCountry());
    }

    @Test
    public void FindAllByCountryFailTest() {
        List<Internship> internships = internshipDao.FindAllByCountry("Angleterre");
        assertEquals(0, internships.size());
    }

    @Test
    public void FindAllByDateTest() {
        List<Internship> internships = internshipDao.FindAllByDate(Date.from(Instant.ofEpochSecond(1111111111100l)));
        assertEquals(1, internships.size());
    }

    @Test
    public void FindAllByDateFailTest() {
        List<Internship> internships = internshipDao.FindAllByDate(Date.from(Instant.ofEpochSecond(1111111111122l)));
        assertEquals(0, internships.size());
    }

    @Test
    public void FindAllByEmailTest() {
        List<Internship> internships = internshipDao.FindAllByEmail("oui.non@telecom-st-etienne.fr");
        assertEquals(1, internships.size());
    }

    @Test
    public void FindAllByEmailFailTest() {
        List<Internship> internships = internshipDao.FindAllByEmail("test@univ-st-etienne.fr");
        assertEquals(0, internships.size());
    }

    @Test
    public void FindAllByFirstnameAndLastnameTest() {
        List<Internship> internships = internshipDao.FindAllByFirstnameAndLastname("Oui", "Non");
        assertEquals(1, internships.size());
    }

    @Test
    public void FindAllByFirstnameAndLastnameFailTest() {
        List<Internship> internships = internshipDao.FindAllByFirstnameAndLastname("Oui", "Oui");
        assertEquals(0, internships.size());
    }
}
