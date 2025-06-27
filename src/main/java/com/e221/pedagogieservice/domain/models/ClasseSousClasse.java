package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class ClasseSousClasse implements GenericEntity<ClasseSousClasse> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "classe", referencedColumnName = "id")
    private Classe classe;
    @ManyToOne
    @JoinColumn(name = "sousClasse", referencedColumnName = "id")
    private SousClasse sousClasse;


    @Override
    public ClasseSousClasse createNewInstance() {
        return new ClasseSousClasse();
    }

    @Override
    public Long getId() {
        return id;
    }
}
