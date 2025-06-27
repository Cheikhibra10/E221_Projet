package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Data;


@Data
@Entity
public class MentionModule implements GenericEntity<MentionModule> {
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
    @JoinColumn(name = "module", referencedColumnName = "id")
    private Module module;

    @Override
    public MentionModule createNewInstance() {
        return new MentionModule();
    }

    @Override
    public Long getId() {
        return id;
    }
}
