package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
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
public class Evenement implements GenericEntity<Evenement> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @ManyToOne
    @JoinColumn(name = "semestre", referencedColumnName = "id")
    private Semestre semestre;
    @ManyToOne
    @JoinColumn(name = "niveau", referencedColumnName = "id")
    private Niveau niveau;
    @Override
    public Evenement createNewInstance() {
        return new Evenement();
    }

    @Override
    public Long getId() {
        return id;
    }
}
