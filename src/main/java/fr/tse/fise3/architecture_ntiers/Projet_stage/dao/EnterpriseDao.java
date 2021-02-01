package fr.tse.fise3.architecture_ntiers.Projet_stage.dao;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Enterprise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class EnterpriseDao {
    private static final Logger LOG = LoggerFactory.getLogger(EnterpriseDao.class);
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    public Enterprise create(String name, String address, String city, String country,
                       String activity, Integer noSIRET, Integer employeeNumber) {
        Enterprise enterprise = new Enterprise();
        enterprise.setName(name);
        enterprise.setAddress(address);
        enterprise.setCity(city);
        enterprise.setCountry(country);
        enterprise.setActivity(activity);
        enterprise.setNoSIRET(noSIRET);
        enterprise.setEmployeeNumber(employeeNumber);
        em.persist(enterprise);
        LOG.info(enterprise + " saved to database.");
        return enterprise;
    }
}
