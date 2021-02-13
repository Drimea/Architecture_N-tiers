package fr.tse.fise3.architecture_ntiers.Projet_stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Mobility {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String country;
    private LocalDate beginDate;
    private LocalDate endDate;
    @ManyToOne
    @JsonIgnoreProperties("mobilities")
    @JoinColumn(name="USER_ID", nullable=false)
    private User student;
}
