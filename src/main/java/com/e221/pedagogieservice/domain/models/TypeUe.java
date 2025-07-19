package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TypeUe implements GenericEntity<TypeUe> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public TypeUe createNewInstance() {
        return new TypeUe();
    }
}
