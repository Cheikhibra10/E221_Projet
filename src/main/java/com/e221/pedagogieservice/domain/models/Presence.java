package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Presence implements GenericEntity<Presence> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean etat;
    private String motif;

    @ManyToOne
    @JoinColumn(name = "inscription", referencedColumnName = "id")
    private Inscription inscription;

    @ManyToOne
    @JoinColumn(name = "Jour", referencedColumnName = "id")
    private Jour jour;

    @ManyToOne
    @JoinColumn(name = "planificationCours", referencedColumnName = "id")
    private PlanificationCours planificationCours;

    @Override
    public Presence createNewInstance() {
        return new Presence();
    }

    @Override
    public Long getId() {
        return id;
    }
}
