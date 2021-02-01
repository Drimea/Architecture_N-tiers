package fr.tse.fise3.architecture_ntiers.Projet_stage.utils;


import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.EnterpriseDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.InternshipDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.UserDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Enterprise;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Configuration
@Slf4j
public class LoadDataBase {

    @Bean
    @Profile("test")
    CommandLineRunner initDatabase(InternshipDao internshipDao,
                                   UserDao userDao,
                                   EnterpriseDao enterpriseDao) {

        return args -> {
            initRepositories(internshipDao, userDao, enterpriseDao);
        };
    }

    private void initRepositories(InternshipDao internshipDao, UserDao userDao,
                                  EnterpriseDao enterpriseDao) {
        Enterprise ent1 = new Enterprise();
        ent1.setActivity("Rechecrhe");
        ent1.setAddress("25 rue du Docteur Rémy Annino");
        ent1.setCity("Saint-Etienne");
        ent1.setCountry("France");
        ent1.setEmployeeNumber(50);
        ent1.setName("Laboratoire Hubert Curien");
        ent1.setNoSIRET(123456789);
        enterpriseDao.create(ent1);

        User user1 = userDao.create("oui.non@telecom-st-etienne.fr",
                "Oui", "Non", "0u1N0n");

        Internship internship1 = new Internship();
        internship1.setBeginDate(LocalDate.now());
        internship1.setEndDate(LocalDate.of(2050, 11, 3));
        internship1.setEnterprise(ent1);
        internship1.setIntern(user1);
        internship1.setType("SFE");
        internshipDao.create(internship1);
    }


}
