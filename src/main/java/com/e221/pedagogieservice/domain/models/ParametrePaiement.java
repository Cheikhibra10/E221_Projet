package com.e221.pedagogieservice.domain.models;

import java.util.Date;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
public class ParametrePaiement implements GenericEntity<ParametrePaiement> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDebutPaiement;
    private Date dateFinPaiement;

    @Override
    public ParametrePaiement createNewInstance() {
        return new ParametrePaiement();
    }

    @Override
    public Long getId() {
        return id;
    }
}
