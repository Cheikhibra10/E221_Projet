package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Calendrier implements GenericEntity<Calendrier> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;
    @ManyToOne
    @JoinColumn(name = "niveau", referencedColumnName = "id")
    private Niveau niveau;
    @ManyToOne
    @JoinColumn(name = "semestre", referencedColumnName = "id")
    private Semestre semestre;
    @OneToMany(mappedBy = "calendrier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvenementCalendrier> evenementCalendriers = new ArrayList<>();
    @Override
    public Calendrier createNewInstance() {
        return new Calendrier();
    }

    @Override
    public Long getId() {
        return id;
    }
}
