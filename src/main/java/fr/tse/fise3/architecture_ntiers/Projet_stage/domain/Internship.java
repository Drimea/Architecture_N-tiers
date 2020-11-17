package fr.tse.fise3.architecture_ntiers.Projet_stage.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Internship {
    @Id
    @GeneratedValue
    private Long id;
    private Date beginDate;
    private Date endDate;
    private String type;

    @ManyToOne
    private User intern;
    @ManyToOne
    private Enterprise enterprise;
}
