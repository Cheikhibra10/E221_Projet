package com.e221.pedagogieservice.domain.repositories;


import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Avance;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvanceRepository extends GenericRepository<Avance> {

    @Query("SELECT a FROM Avance a")
    Page<Avance> findAllPageableAvance(Pageable pageable);
}
