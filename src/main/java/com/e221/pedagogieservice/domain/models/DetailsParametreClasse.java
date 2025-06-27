package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class DetailsParametreClasse implements GenericEntity<DetailsParametreClasse> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean etat;

    @ManyToOne
    @JoinColumn(name = "parametrePaiement", referencedColumnName = "id")
    private ParametrePaiement parametrePaiement;

    @ManyToOne
    @JoinColumn(name = "classe", referencedColumnName = "id")
    private Classe classe;


    @Override
    public DetailsParametreClasse createNewInstance() {
        return new DetailsParametreClasse();
    }

    @Override
    public Long getId() {
        return id;
    }
}
