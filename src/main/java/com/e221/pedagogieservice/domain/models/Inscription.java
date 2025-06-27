package com.e221.pedagogieservice.domain.models;

import java.sql.Timestamp;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
public class Inscription implements GenericEntity<Inscription> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp date;
    private int passe;
    private Timestamp dateAlertePaiement;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "promotion", referencedColumnName = "id")
    private Promotion promotion;
    @ManyToOne
    @JoinColumn(name = "sousClasse", referencedColumnName = "id")
    private SousClasse sousClasse;
    @ManyToOne
    @JoinColumn(name = "anneeScolaire", referencedColumnName = "id")
    private AnneeScolaire anneeScolaire;
    @ManyToOne
    @JoinColumn(name = "etudiant", referencedColumnName = "id")
    private Etudiant etudiant;
    @ManyToOne
    @JoinColumn(name = "bourse", referencedColumnName = "id")
    private Bourse bourse;
    @ManyToOne
    @JoinColumn(name = "parametrePaiement", referencedColumnName = "id")
    private ParametrePaiement parametrePaiement;


    @Override
    public Inscription createNewInstance() {
        return new Inscription();
    }

    @Override
    public Long getId() {
        return id;
    }
}
