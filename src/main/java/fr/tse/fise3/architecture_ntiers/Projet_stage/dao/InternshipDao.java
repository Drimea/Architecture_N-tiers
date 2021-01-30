package fr.tse.fise3.architecture_ntiers.Projet_stage.dao;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
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
public class InternshipDao {

    private static final Logger LOG = LoggerFactory.getLogger(InternshipDao.class);
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    public List<Internship> FindAllByCountry(String country) {
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
}
