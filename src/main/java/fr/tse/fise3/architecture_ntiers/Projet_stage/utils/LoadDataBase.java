package fr.tse.fise3.architecture_ntiers.Projet_stage.utils;


import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.MobilityDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.UserDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Slf4j
public class LoadDataBase {

    @Bean
    @Profile("test")
    CommandLineRunner initDatabase(UserDao userDao,
                                   MobilityDao mobilityDao) {

        return args -> {
            initRepositories(userDao, mobilityDao);
        };
    }

    private void initRepositories(UserDao userDao, MobilityDao mobilityDao) {


        User user1 = userDao.create("oui.non@telecom-st-etienne.fr",
                "Oui", "Non", "0u1N0n", Constants.TYPE_USER_FISE2);
        


        mobilityDao.create(user1, "France", "Saint-Etienne", LocalDate.now(),
                LocalDate.of(2050, 11, 3));

    }


}
