package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Specialite implements GenericEntity<Specialite> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String num;
    private boolean etat;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "mention", referencedColumnName = "id")
    private Mention mention;


    @Override
    public Specialite createNewInstance() {
        return new Specialite();
    }
    @Override
    public Long getId() {
        return id;
    }
}
