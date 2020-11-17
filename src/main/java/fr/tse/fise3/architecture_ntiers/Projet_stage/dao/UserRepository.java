package fr.tse.fise3.architecture_ntiers.Projet_stage.dao;

import fr.tse.fise3.architecture_ntiers.Projet_stage.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
