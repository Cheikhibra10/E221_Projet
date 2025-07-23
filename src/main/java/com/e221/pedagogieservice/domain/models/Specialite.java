package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Specialite implements GenericEntity<Specialite> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statut = Statut.Actif;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;
    @ManyToOne
    @JoinColumn(name = "mention_id", nullable = false)
    private Mention mention;
    @ManyToOne
    @JoinColumn(name = "domaine_id", nullable = false)
    private Domaine domaine;
    @OneToMany(mappedBy = "specialite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NiveauSpecialite> niveauxSpecialites = new ArrayList<>();


    @Override
    public Specialite createNewInstance() {
        return new Specialite();
    }
    @Override
    public Long getId() {
        return id;
    }
}
