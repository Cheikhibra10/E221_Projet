package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class DocumentPourEtudiant implements GenericEntity<DocumentPourEtudiant> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int prix;

    @ManyToOne
    @JoinColumn(name = "document", referencedColumnName = "id")
    private Document document;
    @ManyToOne
    @JoinColumn(name = "inscription", referencedColumnName = "id")
    private Inscription inscription;

    @Override
    public DocumentPourEtudiant createNewInstance() {
        return new DocumentPourEtudiant();
    }

    @Override
    public Long getId() {
        return id;
    }
}
