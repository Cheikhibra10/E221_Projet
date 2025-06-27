package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class TypeReglement implements GenericEntity<TypeReglement> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;


    @Override
    public TypeReglement createNewInstance() {
        return new TypeReglement();
    }

    @Override
    public Long getId() {
        return id;
    }
}
