package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Devoir implements GenericEntity<Devoir> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;
    private Timestamp dateSaisie;
    private Timestamp dateMAJ;
    private double noteDevoire;


    @ManyToOne
    @JoinColumn(name = "note", referencedColumnName = "id")
    private Note note;


    @Override
    public Devoir createNewInstance() {
        return new Devoir();
    }

    @Override
    public Long getId() {
        return id;
    }
}
