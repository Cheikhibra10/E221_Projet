package com.e221.pedagogieservice.domain.models;

import com.cheikh.commun.core.GenericEntity;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class NoteProgrammeModule implements Comparable<NoteProgrammeModule>, GenericEntity<NoteProgrammeModule> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "note", referencedColumnName = "id")
    private Note note;
    @ManyToOne
    @JoinColumn(name = "programmeModule", referencedColumnName = "id")
    private ProgrammeModule programmeModule;

    @Override
    public int compareTo(NoteProgrammeModule o) {
        int compareNum = Integer.parseInt(o.getProgrammeModule().getNum());

        return Integer.parseInt(this.programmeModule.getNum()) - compareNum;
    }


    @Override
    public NoteProgrammeModule createNewInstance() {
        return new NoteProgrammeModule();
    }

    @Override
    public Long getId() {
        return id;
    }
}
