package com.e221.pedagogieservice.domain.models;

import java.sql.Timestamp;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Note implements GenericEntity<Note> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp dateSaisie;
    private Timestamp dateMAJ;
    // moyenne des devoirs
    private double mds;
    // note examen final
    private double nef;
    // 0 = session normale; 1 = session remplacement;
    private int session;

    @ManyToOne
    @JoinColumn(name = "inscription", referencedColumnName = "id")
    private Inscription inscription;


    @Override
    public Note createNewInstance() {
        return new Note();
    }

    @Override
    public Long getId() {
        return id;
    }
}
