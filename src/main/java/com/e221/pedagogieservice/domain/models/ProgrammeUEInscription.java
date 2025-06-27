package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class ProgrammeUEInscription implements GenericEntity<ProgrammeUEInscription> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    private boolean valide;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "programmeUE", referencedColumnName = "id")
    private ProgrammeUE programmeUE;
    @ManyToOne
    @JoinColumn(name = "inscription", referencedColumnName = "id")
    private Inscription inscription;

    @Override
    public ProgrammeUEInscription createNewInstance() {
        return new ProgrammeUEInscription();
    }

    @Override
    public Long getId() {
        return id;
    }
}
