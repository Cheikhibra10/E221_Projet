package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class NiveauSpecialite implements GenericEntity<NiveauSpecialite> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "niveau_id", nullable = false)
    @JsonIgnore
    private Niveau niveau;
    @ManyToOne
    @JoinColumn(name = "specialite_id", nullable = false)
    @JsonIgnore
    private Specialite specialite;


    @Override
    public NiveauSpecialite createNewInstance() {
        return new NiveauSpecialite();
    }

    @Override
    public Long getId() {
        return id;
    }
}
