package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
public class DocumentParNiveau implements GenericEntity<DocumentParNiveau> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    private boolean fournir;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    @JsonIgnore
    private Document document;
    @ManyToOne
    @JoinColumn(name = "niveau_id", nullable = false)
    private Niveau niveau;

    @Override
    public DocumentParNiveau createNewInstance() {
        return new DocumentParNiveau();
    }

    @Override
    public Long getId() {
        return id;
    }
}
