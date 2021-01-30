package fr.tse.fise3.architecture_ntiers.Projet_stage.dao;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class InternshipDao {

    private static final Logger LOG = LoggerFactory.getLogger(InternshipDao.class);
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    public List<Internship> FindAllByCountry(String country) {
        // Il s'agit d'une query JPQL qui ressemble au SQL mais qui fonctionne
        // un peu différemment.
        Query q = em.createQuery(
                "SELECT DISTINCT i " +
                        "FROM Internship i JOIN i.enterprise e " +
                        "WHERE e.country = :country");
        q.setParameter("country", country);
        return q.getResultList();
    }

    public void create(Internship internship) {
        em.persist(internship);
    }

    public List<Internship> FindAllByDate(LocalDate date) {
        Query q = em.createQuery(
                "SELECT DISTINCT i " +
                        "FROM Internship i " +
                        "WHERE :date BETWEEN i.beginDate AND i.endDate");
        q.setParameter("date", date);
        return q.getResultList();
    }

    public List<Internship> FindAllByEmail(String email) {
        Query q = em.createQuery(
                "SELECT DISTINCT i " +
                        "FROM Internship i JOIN i.intern u " +
                        "WHERE u.email = :email");
        q.setParameter("email", email);
        return q.getResultList();
    }

    public List<Internship> FindAllByFirstnameAndLastname(String firstname, String lastname) {
        Query q = em.createQuery(
                "SELECT DISTINCT i " +
                        "FROM Internship i JOIN i.intern u " +
                        "WHERE u.firstname = :firstname AND u.lastname = :lastname");
        q.setParameter("firstname", firstname);
        q.setParameter("lastname", lastname);
        return q.getResultList();
    }
}
