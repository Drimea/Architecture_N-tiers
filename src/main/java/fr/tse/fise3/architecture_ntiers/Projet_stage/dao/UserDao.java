package fr.tse.fise3.architecture_ntiers.Projet_stage.dao;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    public User create(String email, String firstname, String lastname, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFirstname(firstname);
        newUser.setLastname(lastname);
        newUser.setPassword(password);
        em.persist(newUser);
        LOG.info(newUser + " saved to database.");
        return newUser;
    }


    public void update(User user) {
        em.merge(user);
        LOG.info(user + " updated in database.");
    }

    public User find(User user) {
        User userDB = em.getReference(User.class, user.getId());
        return userDB;
    }

    public void delete(User user) {
        User fromDB = em.getReference(User.class, user.getId());
        em.remove(fromDB);
        LOG.info(user + " removed from database.");
    }

    public List<User> listUsers() {
        Query q = em.createQuery("FROM User");
        List<User> list = q.getResultList();
        return list;
    }

    public User findUserById(Long id) {
        Query q = em.createQuery("FROM User u " +
                "WHERE u.id = :id");
        q.setParameter("id", id);
        List<User> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public User findUserByEmail(String email) {
        Query q = em.createQuery("FROM User u " +
                "WHERE u.email = :email");
        q.setParameter("email", email);
        List<User> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public User findUserByFirstnameAndLastname(String firstname, String lastname) {
        Query q = em.createQuery("FROM User u " +
                "WHERE u.firstname = :firstname AND u.lastname = :lastname");
        q.setParameter("firstname", firstname);
        q.setParameter("lastname", lastname);
        List<User> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
