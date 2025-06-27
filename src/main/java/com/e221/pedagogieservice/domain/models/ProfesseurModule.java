package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;

@Data
@Entity
@ToString
public class ProfesseurModule implements GenericEntity<ProfesseurModule> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professeur", referencedColumnName = "id")
    private Professeur professeur;
    @ManyToOne
    @JoinColumn(name = "module", referencedColumnName = "id")
    private Module module;

    @Override
    public ProfesseurModule createNewInstance() {
        return new ProfesseurModule();
    }

    @Override
    public Long getId() {
        return id;
    }
}
