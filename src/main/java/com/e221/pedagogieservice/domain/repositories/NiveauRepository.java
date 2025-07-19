package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.models.Niveau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NiveauRepository extends GenericRepository<Niveau> {
    @Query("SELECT e FROM Niveau e WHERE e.archive = false")
    List<Niveau> findAllActive();

    @Query("SELECT n FROM Niveau n")
    Page<Niveau> findAllPageableNiveau(Pageable pageable);

}
