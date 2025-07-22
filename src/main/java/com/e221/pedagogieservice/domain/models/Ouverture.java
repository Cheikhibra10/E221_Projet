package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Ouverture implements GenericEntity<Ouverture> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "anneeScolaire_id", nullable = false)
    private AnneeScolaire anneeScolaire;

    @Override
    public Ouverture createNewInstance() {
        return new Ouverture();
    }

    @Override
    public Long getId() {
        return id;
    }
}
