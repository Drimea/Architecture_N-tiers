package fr.tse.fise3.architecture_ntiers.Projet_stage.utils;


import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.EnterpriseRepository;
import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.InternshipRepository;
import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.UserRepository;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Enterprise;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Date;

@Configuration
@Slf4j
public class LoadDataBase {

    @Bean
    @Profile("test")
    CommandLineRunner initDatabase(InternshipRepository internshipRepository,
                                   EnterpriseRepository enterpriseRepository,
                                   UserRepository userRepository) {

        return args -> {
            initRepositories(internshipRepository, enterpriseRepository, userRepository);
        };
    }

    private void initRepositories(InternshipRepository internshipRepository,
                                  EnterpriseRepository enterpriseRepository,
                                  UserRepository userRepository) {
        Enterprise ent1 = new Enterprise();
        ent1.setActivity("Rechecrhe");
        ent1.setAddress("25 rue du Docteur Rémy Annino");
        ent1.setCity("Saint-Etienne");
        ent1.setCountry("France");
        ent1.setEmployeeNumber(50);
        ent1.setName("Laboratoire Hubert Curien");
        ent1.setNoSIRET(123456789);
        enterpriseRepository.save(ent1);

        User user1 = new User();
        user1.setEmail("oui.non@telecom-st-etienne.fr");
        user1.setFirstname("Oui");
        user1.setLastname("Non");
        user1.setPassword("0u1N0n");
        userRepository.save(user1);

        Internship internship1 = new Internship();
        internship1.setBeginDate(Date.from(Instant.now()));
        internship1.setEndDate(Date.from(Instant.ofEpochSecond(1111111111111l)));
        internship1.setEnterprise(ent1);
        internship1.setIntern(user1);
        internship1.setType("SFE");
    }


}
