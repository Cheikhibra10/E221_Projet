package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.AnneeScolaire;
import com.e221.pedagogieservice.domain.models.Classe;
import com.e221.pedagogieservice.domain.models.ClasseReferentiel;
import com.e221.pedagogieservice.domain.models.Referentiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseReferentielRepository extends JpaRepository<ClasseReferentiel, Long> {
}
