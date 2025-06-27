package com.e221.pedagogieservice.domain.models;


import com.cheikh.commun.core.GenericEntity;
import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class RecapSemestreInscriptionValide implements GenericEntity<RecapSemestreInscriptionValide> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private boolean valide;
  private Integer nombreCreditSemestre;

  @ManyToOne
  @JoinColumn(name = "semestre", referencedColumnName = "id")
  private Semestre semestre;
  @ManyToOne
  @JoinColumn(name = "inscription", referencedColumnName = "id")
  private Inscription inscription;


  @Override
  public RecapSemestreInscriptionValide createNewInstance() {
    return new RecapSemestreInscriptionValide();
  }

  @Override
  public Long getId() {
    return id;
  }
}
