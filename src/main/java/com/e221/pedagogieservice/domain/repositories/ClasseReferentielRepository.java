package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.AnneeScolaire;
import com.e221.pedagogieservice.domain.models.Classe;
import com.e221.pedagogieservice.domain.models.ClasseReferentiel;
import com.e221.pedagogieservice.domain.models.Referentiel;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseReferentielRepository extends GenericRepository<ClasseReferentiel> {

    @Query("SELECT c FROM ClasseReferentiel c")
    Page<ClasseReferentiel> findAllPageableClasseReferentiel(Pageable pageable);
}
