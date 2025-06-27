package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Document implements GenericEntity<Document> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @Override
    public Document createNewInstance() {
        return new Document();
    }

    @Override
    public Long getId() {
        return id;
    }
}
