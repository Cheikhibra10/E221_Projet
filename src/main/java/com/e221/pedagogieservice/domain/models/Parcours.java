package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Parcours implements GenericEntity<Parcours> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private boolean etat;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @Override
    public Parcours createNewInstance() {
        return new Parcours() ;
    }

    @Override
    public Long getId() {
        return id;
    }
}
