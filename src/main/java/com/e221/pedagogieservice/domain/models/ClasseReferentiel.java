package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class ClasseReferentiel implements GenericEntity<ClasseReferentiel> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int anneeDebut;
    private int anneeFin;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;
    
    @ManyToOne
    @JoinColumn(name = "anneeScolaire", referencedColumnName = "id")
    private AnneeScolaire anneeScolaire;
    @ManyToOne
    @JoinColumn(name = "classe", referencedColumnName = "id")
    private Classe classe;
    @ManyToOne
    @JoinColumn(name = "referentiel", referencedColumnName = "id")
    private Referentiel referentiel;


    @Override
    public ClasseReferentiel createNewInstance() {
        return new ClasseReferentiel();
    }

    @Override
    public Long getId() {
        return id;
    }
}
