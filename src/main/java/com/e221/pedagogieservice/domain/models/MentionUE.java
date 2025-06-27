package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class MentionUE implements GenericEntity<MentionUE> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    @Schema(description = "Archivé ou non", defaultValue = "false", example = "false")
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "mention", referencedColumnName = "id")
    private Mention mention;
    @ManyToOne
    @JoinColumn(name = "ue", referencedColumnName = "id")
    private UE ue;


    @Override
    public MentionUE createNewInstance() {
        return new MentionUE();
    }

    @Override
    public Long getId() {
        return id;
    }
}
