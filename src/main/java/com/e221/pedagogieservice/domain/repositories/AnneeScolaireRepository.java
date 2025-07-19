package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.AnneeScolaire;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface AnneeScolaireRepository extends GenericRepository<AnneeScolaire> {
    @Query("SELECT a FROM AnneeScolaire a")
    Page<AnneeScolaire> findAllPageableAnnee(Pageable pageable);

}
