package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UE implements GenericEntity<UE> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String libelle;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;
    @OneToMany(mappedBy = "ue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MentionUE> mentionUES = new ArrayList<>();


    @Override
    public UE createNewInstance() {
        return new UE();
    }

    @Override
    public Long getId() {
        return id;
    }
}
