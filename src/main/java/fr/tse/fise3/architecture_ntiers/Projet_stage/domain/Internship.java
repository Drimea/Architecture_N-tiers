package fr.tse.fise3.architecture_ntiers.Projet_stage.domain;

import lombok.Data;

import javax.persistence.*;
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
    @ManyToOne()
    @JoinColumn(name="ENTERPRISE_ID", nullable=true)
    private Enterprise enterprise;
}
