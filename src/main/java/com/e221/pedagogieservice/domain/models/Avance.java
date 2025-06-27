package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Avance implements GenericEntity<Avance> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int montant;

    @ManyToOne
    @JoinColumn(name = "reglement", referencedColumnName = "id")
    private Reglement reglement;


    @Override
    public Avance createNewInstance() {
        return new Avance();
    }

    @Override
    public Long getId() {
        return id;
    }
}
