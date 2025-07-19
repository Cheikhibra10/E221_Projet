package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.models.EvenementCalendrier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends GenericRepository<Evenement> {

    @Query("SELECT e FROM Evenement e")
    Page<Evenement> findAllPageableEvenement(Pageable pageable);

}
