package fr.tse.fise3.architecture_ntiers.Projet_stage;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class ProjetStageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetStageApplication.class, args);
	}

}
