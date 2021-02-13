package fr.tse.fise3.architecture_ntiers.Projet_stage.controller;

import fr.tse.fise3.architecture_ntiers.Projet_stage.dao.UserDao;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userDao.listUsers();
    }
}
