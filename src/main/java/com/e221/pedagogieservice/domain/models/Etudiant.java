package com.e221.pedagogieservice.domain.models;

import java.util.Date;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Etudiant implements GenericEntity<Etudiant> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cin;
    private String matricule;
    private int abandon;
    private String nom;
    private String prenom;
    private String email;
    private String emailPro;
    private String telephone;
    private Date dateNaissance;
    private String lieuNaissance;
    private String genre;
    private String etablissementPrecedent;
    private String motifEntree;
    private String niveauEntree;
    private String metier;
    private String ambition;
    private String autresRenseignements;
    private String photo;
    @Transient
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "pays", referencedColumnName = "id")
    private Pays pays;

    @Override
    public Etudiant createNewInstance() {
        return new Etudiant();
    }

    @Override
    public Long getId() {
        return id;
    }
}
