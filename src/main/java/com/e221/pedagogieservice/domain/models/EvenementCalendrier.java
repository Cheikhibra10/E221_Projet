package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class EvenementCalendrier implements GenericEntity<EvenementCalendrier> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private Statut statut;
    private Date dateDebut;
    private Date dateFin;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;
    @ManyToOne
    @JoinColumn(name = "calendrier_id", nullable = false)
    @JsonIgnore
    private Calendrier calendrier;
    @ManyToOne
    @JoinColumn(name = "evenement_id", nullable = false)
    private Evenement evenement;

    @Override
    public EvenementCalendrier createNewInstance() {
        return new EvenementCalendrier();
    }

    @Override
    public Long getId() {
        return id;
    }

}
