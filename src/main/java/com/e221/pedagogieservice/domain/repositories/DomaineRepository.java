package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Domaine;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomaineRepository extends GenericRepository<Domaine> {

    @Query("SELECT d FROM Domaine d")
    Page<Domaine> findAllPageableDomaine(Pageable pageable);

    Optional<Domaine> findByLibelle(String libelle);
}
