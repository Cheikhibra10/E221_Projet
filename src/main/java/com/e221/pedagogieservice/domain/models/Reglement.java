package com.e221.pedagogieservice.domain.models;

import java.sql.Timestamp;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Reglement implements GenericEntity<Reglement> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp date;
    private int montant;
    private int montantRestant;

    @ManyToOne
    @JoinColumn(name = "mois", referencedColumnName = "id")
    private Mois mois;
    @ManyToOne
    @JoinColumn(name = "inscription", referencedColumnName = "id")
    private Inscription inscription;
    @ManyToOne
    @JoinColumn(name = "typeReglement", referencedColumnName = "id")
    private TypeReglement typeReglement;


    @Override
    public Reglement createNewInstance() {
        return new Reglement();
    }

    @Override
    public Long getId() {
        return id;
    }
}
