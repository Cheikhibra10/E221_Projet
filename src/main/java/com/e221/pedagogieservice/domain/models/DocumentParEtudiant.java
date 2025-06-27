package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class DocumentParEtudiant implements GenericEntity<DocumentParEtudiant> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Transient
    private String file;
    @Transient
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "etudiant", referencedColumnName = "id")
    private Etudiant etudiant;
    @ManyToOne
    @JoinColumn(name = "document", referencedColumnName = "id")
    private Document document;


    @Override
    public DocumentParEtudiant createNewInstance() {
        return new DocumentParEtudiant();
    }

    @Override
    public Long getId() {
        return id;
    }
}
