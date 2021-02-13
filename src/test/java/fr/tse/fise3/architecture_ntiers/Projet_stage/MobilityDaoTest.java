package fr.tse.fise3.architecture_ntiers.Projet_stage;

import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.MobilityDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Mobility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class MobilityDaoTest {

    @Autowired
    MobilityDao mobilityDao;

    @Test
    public void FindAllByCountryTest() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("country", "France");
        List<Mobility> mobilities = mobilityDao.findAllByCriteria(criteria);
        assertEquals(1, mobilities.size());
        assertEquals("France", mobilities.get(0).getCountry());
    }

    @Test
    public void FindAllByDateTest() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("date", LocalDate.of(2049, 11, 3));
        List<Mobility> mobilities = mobilityDao.findAllByCriteria(criteria);
        assertEquals(1, mobilities.size());
    }
}
