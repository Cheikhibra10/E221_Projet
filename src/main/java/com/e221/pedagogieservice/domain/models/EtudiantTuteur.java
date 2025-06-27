package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class EtudiantTuteur implements GenericEntity<EtudiantTuteur> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etudiant", referencedColumnName = "id")
    private Etudiant etudiant;
    @ManyToOne
    @JoinColumn(name = "tuteur", referencedColumnName = "id")
    private Utilisateur tuteur;

    @Override
    public EtudiantTuteur createNewInstance() {
        return new EtudiantTuteur();
    }

    @Override
    public Long getId() {
        return id;
    }
}
