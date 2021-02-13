package fr.tse.fise3.architecture_ntiers.Projet_stage;

import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.UserDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import fr.tse.fise3.architecture_ntiers.Projet_stage.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void createAndDeleteUserTest() {
        int sizeDao = userDao.listUsers().size();
        User testUser = userDao.create("test@test.test", "Testfirstname",
                "Testlastname", "M0tDeP4ss3", Constants.TYPE_USER_CITISE1);
        assertEquals(sizeDao+1, userDao.listUsers().size());
        assertNotNull(userDao.find(testUser));

        userDao.delete(testUser);
        assertEquals(sizeDao, userDao.listUsers().size());
    }

    @Test
    public void createAndUpdateUserTest() {
        int sizeDao = userDao.listUsers().size();
        User testUser = userDao.create("test@test.test", "Testfirstname",
                "Testlastname", "M0tDeP4ss3", Constants.TYPE_USER_ADMIN);
        assertEquals(sizeDao+1, userDao.listUsers().size());
        assertNotNull(userDao.find(testUser));


        Long id = testUser.getId();
        String newEmail = "address.test@test.test";
        testUser.setEmail(newEmail);
        userDao.update(testUser);
        assertEquals(newEmail, userDao.findUserById(id).getEmail());
    }

    @Test
    public void findUserByEmailTest() {
        User user = userDao.findUserByEmail("oui.non@telecom-st-etienne.fr");
        assertNotNull(user);
    }

    @Test
    public void findUserByFirstnameAndLastnameTest() {
        User user = userDao.findUserByFirstnameAndLastname("Oui", "Non");
        assertNotNull(user);
    }
}
