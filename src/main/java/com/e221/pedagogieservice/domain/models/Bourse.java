package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
public class Bourse implements GenericEntity<Bourse> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean entier;
    private boolean demi;
    private boolean payant;

    @Override
    public Bourse createNewInstance() {
        return new Bourse();
    }

    @Override
    public Long getId() {
        return id;
    }
}
