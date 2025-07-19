package com.e221.pedagogieservice.domain.repositories;


import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.models.Semestre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SemestreRepository extends GenericRepository<Semestre> {

    @Query("SELECT s FROM Semestre s")
    Page<Semestre> findAllPageableSemestre(Pageable pageable);

}
