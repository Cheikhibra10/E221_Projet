package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Frais implements GenericEntity<Frais> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;


    @Override
    public Frais createNewInstance() {
        return new Frais();
    }

    @Override
    public Long getId() {
        return id;
    }
}
