package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
//import com.e221.pedagogieservice.domain.audit.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class AnneeScolaire implements GenericEntity<AnneeScolaire> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private int annee;
    private boolean enCours;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;


    @Override
    public AnneeScolaire createNewInstance() {
        return new AnneeScolaire();
    }

    @Override
    public Long getId() {
        return id;
    }
}
