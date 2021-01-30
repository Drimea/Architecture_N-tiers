package fr.tse.fise3.architecture_ntiers.Projet_stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Internship {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String type;

    @ManyToOne
    @JsonIgnoreProperties("internships")
    @JoinColumn(name="USER_ID", nullable=false)
    private User intern;
    @ManyToOne
    @JsonIgnoreProperties("internships")
    @JoinColumn(name="ENTERPRISE_ID", nullable=false)
    private Enterprise enterprise;
}
