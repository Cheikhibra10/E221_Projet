package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Mois implements GenericEntity<Mois> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;


    @Override
    public Mois createNewInstance() {
        return new Mois();
    }

    @Override
    public Long getId() {
        return id;
    }
}
