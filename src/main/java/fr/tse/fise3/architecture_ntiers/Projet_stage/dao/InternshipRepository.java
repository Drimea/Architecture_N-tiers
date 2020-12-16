package fr.tse.fise3.architecture_ntiers.Projet_stage.dao;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship,Long> {
    @Query("SELECT I.id, I.beginDate, I.endDate, I.type, E.country, E.city " +
            "FROM INTERNSHIP AS I, ENTERPRISE AS E" +
            "WHERE E.country = ?1")
    public List<Internship> FindAllByCountry(String country);
}
