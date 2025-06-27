package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;


@Data
@Entity
public class ProgrammeModule implements GenericEntity<ProgrammeModule> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String num;
    private int budget;
    private int coef;
    private int nbreCreditModule;
    private String syllabus;
    private int td; // nbr heures travaux dirigés
    private int tp; // nbr heures travaux pratique
    private int tpe; // nbr heures travail personnel etudiant
    private int vhp; // volume horaire presentiel
    private int cm; // nbr heures cours magistral
    private int vht; // volume heures total
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "module", referencedColumnName = "id")
    private Module module;
    @ManyToOne
    @JoinColumn(name = "programmeUE", referencedColumnName = "id")
    private ProgrammeUE programmeUE;

    @Override
    public ProgrammeModule createNewInstance() {
        return new ProgrammeModule();
    }

    @Override
    public Long getId() {
        return id;
    }
}
