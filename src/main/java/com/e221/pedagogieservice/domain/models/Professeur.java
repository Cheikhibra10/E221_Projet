package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
public class Professeur implements GenericEntity<Professeur> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private int anneExperience;
    private String cv;
    private Date dateNaissance;
    private String diplome;
    private String email;
    private String grade;
    private String siteWeb;
    private String specialite;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @Override
    public Professeur createNewInstance() {
        return new Professeur();
    }

    @Override
    public Long getId() {
        return id;
    }
}
