package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Calendrier;
import com.e221.pedagogieservice.domain.models.Document;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendrierRepository extends GenericRepository<Calendrier> {

    @Query("""
    select distinct c
    from Calendrier c
    left join fetch c.evenementCalendriers ec
    left join fetch ec.evenement e
    where c.archive = false
""")
    List<Calendrier> findAllActiveWithEvenements();

    @Query("""
    select c
    from Calendrier c
    left join fetch c.evenementCalendriers ec
    left join fetch ec.evenement e
    where c.id = :id
""")
    Optional<Calendrier> findByIdWithEvenements(Long id);

    @Query("SELECT c FROM Calendrier c")
    Page<Calendrier> findAllPageableCalendrier(Pageable pageable);
}
