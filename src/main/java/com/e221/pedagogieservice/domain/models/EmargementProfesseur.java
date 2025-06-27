package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@ToString
public class EmargementProfesseur implements GenericEntity<EmargementProfesseur> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp date;
    private boolean etat;

    @ManyToOne
    @JoinColumn(name = "planificationCours", referencedColumnName = "id")
    private PlanificationCours planificationCours;


    @Override
    public EmargementProfesseur createNewInstance() {
        return new EmargementProfesseur();
    }

    @Override
    public Long getId() {
        return id;
    }
}
