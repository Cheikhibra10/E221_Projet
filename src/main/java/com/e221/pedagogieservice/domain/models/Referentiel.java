package com.e221.pedagogieservice.domain.models;

import java.util.Date;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Referentiel implements GenericEntity<Referentiel> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int annee;
    private int credit;
    private int volumeHeureTotal;
    private Date date;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "niveau", referencedColumnName = "id")
    private Niveau niveau;
    @ManyToOne
    @JoinColumn(name = "specialite", referencedColumnName = "id")
    private Specialite specialite;

    @Override
    public Referentiel createNewInstance() {
        return new Referentiel();
    }

    @Override
    public Long getId() {
        return id;
    }
}
