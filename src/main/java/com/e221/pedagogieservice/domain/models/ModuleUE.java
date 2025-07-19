package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;


@Data
@Entity
public class ModuleUE implements GenericEntity<ModuleUE> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "ue", referencedColumnName = "id")
    private UE ue;
    @ManyToOne
    @JoinColumn(name = "module", referencedColumnName = "id")
    @JsonIgnore
    private Module module;

    @Override
    public ModuleUE createNewInstance() {
        return new ModuleUE();
    }

    @Override
    public Long getId() {
        return id;
    }
}
