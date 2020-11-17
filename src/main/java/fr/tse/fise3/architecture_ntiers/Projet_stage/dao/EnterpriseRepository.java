package fr.tse.fise3.architecture_ntiers.Projet_stage.dao;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise,Long> {
}
