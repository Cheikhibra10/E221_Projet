package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Salle;
import com.e221.pedagogieservice.domain.models.SousClasse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SalleRepository extends GenericRepository<Salle> {

    @Query("SELECT s FROM Salle s")
    Page<Salle> findAllPageableSousSalle(Pageable pageable);

}