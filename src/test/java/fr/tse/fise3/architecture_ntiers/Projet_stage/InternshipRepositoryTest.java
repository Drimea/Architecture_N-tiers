package fr.tse.fise3.architecture_ntiers.Projet_stage;

import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.InternshipRepository;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class InternshipRepositoryTest {

    @Autowired
    private InternshipRepository internshipRepository;

    @Test
    public void FindAllByCountryTest() {
        List<Internship> internships = internshipRepository.FindAllByCountry("France");
        assertEquals(1, internships.size());
        assertEquals("France", internships.get(0).getEnterprise().getCountry());
    }

}
