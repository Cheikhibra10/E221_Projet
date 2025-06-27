package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Utilisateur implements GenericEntity<Utilisateur> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cin;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String profession;
    private String login;
    private String password;
    private String fonction;
    @Column(columnDefinition = "boolean default false")
    private boolean etat;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name="profil", referencedColumnName = "id")
    private Profil profil;

    @Override
    public Utilisateur createNewInstance() {
        return new Utilisateur();
    }

    @Override
    public Long getId() {
        return id;
    }
}
