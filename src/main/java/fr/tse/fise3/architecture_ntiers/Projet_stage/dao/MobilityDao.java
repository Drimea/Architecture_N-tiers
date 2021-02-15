package fr.tse.fise3.architecture_ntiers.Projet_stage.dao;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Mobility;
import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class MobilityDao {
    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    // Cette méthode couvre l'ensemble des requêtes (croisées ou non) possible.
    public List<Mobility> findAllByCriteria(Map<String, Object> criteria) {
        // Il s'agit d'une query JPQL qui ressemble au SQL mais qui fonctionne
        // un peu différemment.
        String stringQuery = "SELECT DISTINCT m FROM Mobility m JOIN m.student s ";

        if (criteria.size() != 0) {
            stringQuery += "WHERE ";
        }
        else {
            Query q = em.createQuery(stringQuery);
            return q.getResultList();
        }

        boolean hasCriteriaBefore = false;
        if (criteria.containsKey("country")) {
            stringQuery += "country = :country ";
            hasCriteriaBefore = true;
        }
        if (criteria.containsKey("firstname")) {
            if (hasCriteriaBefore) {
                stringQuery += "AND ";
            }
            stringQuery += "s.firstname = :firstname ";
            hasCriteriaBefore = true;
        }
        if (criteria.containsKey("lastname")) {
            if (hasCriteriaBefore) {
                stringQuery += "AND ";
            }
            stringQuery += "s.lastname = :lastname ";
            hasCriteriaBefore = true;
        }
        if (criteria.containsKey("email")) {
            if (hasCriteriaBefore) {
                stringQuery += "AND ";
            }
            stringQuery += "s.email = :email ";
            hasCriteriaBefore = true;
        }
        if (criteria.containsKey("date")) {
            if (hasCriteriaBefore) {
                stringQuery += "AND ";
            }
            stringQuery += ":date BETWEEN m.beginDate AND m.endDate ";
            hasCriteriaBefore = true;
        }
        if (criteria.containsKey("typeUser")) {
            if (hasCriteriaBefore) {
                stringQuery += "AND ";
            }
            stringQuery += "s.typeUser = :typeUser ";
        }

        Query q = em.createQuery(stringQuery);

        if (criteria.containsKey("country")) {
            q.setParameter("country", criteria.get("country"));
        }
        if (criteria.containsKey("firstname")) {
            q.setParameter("firstname", criteria.get("firstname"));
        }
        if (criteria.containsKey("lastname")) {
            q.setParameter("lastname", criteria.get("lastname"));
        }
        if (criteria.containsKey("email")) {
            q.setParameter("email", criteria.get("email"));
        }
        if (criteria.containsKey("date")) {
            q.setParameter("date", (LocalDate) criteria.get("date"));
        }
        if (criteria.containsKey("typeUser")) {
            q.setParameter("typeUser", criteria.get("typeUser"));
        }

        return q.getResultList();
    }

    public Mobility findById(Long id) {
        return em.find(Mobility.class, id);
    }

    public Mobility update(Mobility mobility) {
        em.merge(mobility);
        LOG.info(mobility + " updated in database.");
        return this.findById(mobility.getId());
    }

    public void delete(Mobility mobility) {
        Mobility fromDB = em.getReference(Mobility.class, mobility.getId());
        fromDB.getStudent().getMobilities().remove(fromDB);
        em.remove(fromDB);
        LOG.info(fromDB + " removed from database.");
    }

    public Mobility create(User student, String country, String city, LocalDate beginDate, LocalDate endDate) {
        Mobility mobility = new Mobility();
        mobility.setStudent(student);
        mobility.setCountry(country);
        mobility.setCity(city);
        mobility.setBeginDate(beginDate);
        mobility.setEndDate(endDate);
        mobility.setSubmissionDate(LocalDate.now());
        em.persist(mobility);
        LOG.info(mobility + " saved to Database.");
        return mobility;
    }
}
