package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Profil implements GenericEntity<Profil> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;

    @Override
    public Profil createNewInstance() {
        return new Profil();
    }

    @Override
    public Long getId() {
        return id;
    }
}
