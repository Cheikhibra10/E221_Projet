package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
public class PlanificationCours implements GenericEntity<PlanificationCours> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer heureDebut;
    private Integer heureFin;
    private int horaire;
    private Date jour;
    private Date periodeDebut;
    private Date periodeFin;

    @ManyToOne
    @JoinColumn(name = "anneeScolaire", referencedColumnName = "id")
    private AnneeScolaire anneeScolaire;
    @ManyToOne
    @JoinColumn(name = "programmeModule", referencedColumnName = "id")
    private ProgrammeModule programmeModule;

    @Override
    public PlanificationCours createNewInstance() {
        return new PlanificationCours();
    }

    @Override
    public Long getId() {
        return id;
    }
}
