package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;

@Data
@Entity
@ToString
public class TypeContrat implements GenericEntity<TypeContrat> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;


    @Override
    public TypeContrat createNewInstance() {
        return new TypeContrat();
    }

    @Override
    public Long getId() {
        return id;
    }
}
