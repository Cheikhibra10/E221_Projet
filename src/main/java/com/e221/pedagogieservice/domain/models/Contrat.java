package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
public class Contrat implements GenericEntity<Contrat> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private boolean enCours;
    private int horaireVacataire;
    private int nombreHeure;
    private int montantContractuel;
    private String document;

    @ManyToOne
    @JoinColumn(name = "typeContrat", referencedColumnName = "id")
    private TypeContrat typeContrat;
    @ManyToOne
    @JoinColumn(name = "professeur", referencedColumnName = "id")
    private Professeur professeur;


    @Override
    public Contrat createNewInstance() {
        return new Contrat();
    }

    @Override
    public Long getId() {
        return id;
    }
}
