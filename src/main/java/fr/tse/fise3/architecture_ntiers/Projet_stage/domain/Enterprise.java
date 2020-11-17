package fr.tse.fise3.architecture_ntiers.Projet_stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Enterprise {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String activity;
    private Integer noSIRET;
    private Integer employeeNumber;

    @OneToMany(mappedBy="enterprise", fetch = FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval=true)
    @JsonIgnoreProperties("enterprise")
    @EqualsAndHashCode.Exclude
    private Set<Internship> internships;
}
