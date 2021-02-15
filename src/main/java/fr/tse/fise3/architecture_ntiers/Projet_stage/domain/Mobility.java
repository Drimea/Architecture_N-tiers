package fr.tse.fise3.architecture_ntiers.Projet_stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate beginDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate submissionDate;
    @ManyToOne
    @JsonIgnoreProperties("mobilities")
    @JoinColumn(name="USER_ID", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User student;
}
