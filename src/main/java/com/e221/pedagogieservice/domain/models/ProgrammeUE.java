package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class ProgrammeUE implements GenericEntity<ProgrammeUE> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private int credit;
    private int fondamental;
    private int nbreHeureUE;
    private int num;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "referentiel", referencedColumnName = "id")
    private Referentiel referentiel;
    @ManyToOne
    @JoinColumn(name = "semestre", referencedColumnName = "id")
    private Semestre semestre;
    @ManyToOne
    @JoinColumn(name = "ue", referencedColumnName = "id")
    private UE ue;


    @Override
    public ProgrammeUE createNewInstance() {
        return new ProgrammeUE();
    }

    @Override
    public Long getId() {
        return id;
    }
}
