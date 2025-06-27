package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;
import lombok.*;

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
    private boolean statut;

    @Override
    public Evenement createNewInstance() {
        return new Evenement();
    }

    @Override
    public Long getId() {
        return id;
    }
}
