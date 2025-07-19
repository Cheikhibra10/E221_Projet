package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Classe implements GenericEntity<Classe> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String code;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;
    @ManyToOne
    @JoinColumn(name = "domaine", referencedColumnName = "id")
    private Domaine domaine;
    @ManyToOne
    @JoinColumn(name = "niveau", referencedColumnName = "id")
    private Niveau niveau;
    @ManyToOne
    @JoinColumn(name = "specialite", referencedColumnName = "id")
    private Specialite specialite;



    @Override
    public Classe createNewInstance() {
        return new Classe();
    }

    @Override
    public Long getId() {
        return id;
    }
}
