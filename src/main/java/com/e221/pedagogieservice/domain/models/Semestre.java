package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Semestre implements GenericEntity<Semestre> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String duree;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;
    @ManyToOne
    @JoinColumn(name = "niveau", referencedColumnName = "id")
    private Niveau niveau;
    @Override
    public Semestre createNewInstance() {
        return new Semestre();
    }

    @Override
    public Long getId() {
        return id;
    }
}
