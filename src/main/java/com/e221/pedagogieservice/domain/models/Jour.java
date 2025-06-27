package com.e221.pedagogieservice.domain.models;

import java.util.Date;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Jour implements GenericEntity<Jour> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private int jour;

    @Override
    public Jour createNewInstance() {
        return new Jour();
    }

    @Override
    public Long getId() {
        return id;
    }
}
