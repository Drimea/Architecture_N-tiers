package fr.tse.fise3.architecture_ntiers.Projet_stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval=true)
    @JsonIgnoreProperties("user")
    @EqualsAndHashCode.Exclude
    private Set<Internship> internships;
}
