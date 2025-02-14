package fr.tse.fise3.architecture_ntiers.Projet_stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties({"password"})
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String typeUser;


    @OneToMany(mappedBy="student", fetch = FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval=true)
    @JsonIgnoreProperties("student")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Mobility> mobilities;

    public User() {
        this.mobilities = new HashSet<Mobility>();
    }

}
