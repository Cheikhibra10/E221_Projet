package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class ParametreFrais implements GenericEntity<ParametreFrais> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int montant;

    @ManyToOne
    @JoinColumn(name = "frais", referencedColumnName = "id")
    private Frais frais;

    @ManyToOne
    @JoinColumn(name = "parametrePaiement", referencedColumnName = "id")
    private ParametrePaiement parametrePaiement;


    @Override
    public ParametreFrais createNewInstance() {
        return new ParametreFrais();
    }

    @Override
    public Long getId() {
        return id;
    }
}
