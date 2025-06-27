package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;

@Data
@Entity
@ToString
public class ContratProgrammeModule implements GenericEntity<ContratProgrammeModule> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int horaire;

    @ManyToOne
    @JoinColumn(name = "contrat", referencedColumnName = "id")
    private Contrat contrat;
    @ManyToOne
    @JoinColumn(name = "programmeModule", referencedColumnName = "id")
    private ProgrammeModule programmeModule;


    @Override
    public ContratProgrammeModule createNewInstance() {
        return new ContratProgrammeModule();
    }

    @Override
    public Long getId() {
        return id;
    }
}
