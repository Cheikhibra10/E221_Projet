package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Domaine implements GenericEntity<Domaine> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @Column(columnDefinition = "boolean default true")
    @Schema(description = "etat 0 ou 1", defaultValue = "true", example = "true")
    private boolean etat = true;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;


    @Override
    public Domaine createNewInstance() {
        return new Domaine();
    }

    @Override
    public Long getId() {
        return id;
    }
}
