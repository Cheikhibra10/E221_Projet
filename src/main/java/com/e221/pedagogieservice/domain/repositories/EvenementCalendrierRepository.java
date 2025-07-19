package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.EvenementCalendrier;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementCalendrierRepository extends GenericRepository<EvenementCalendrier> {

    @Query("SELECT e FROM EvenementCalendrier e")
    Page<EvenementCalendrier> findAllPageableEvenementCalendrier(Pageable pageable);
}
