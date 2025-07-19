package com.e221.pedagogieservice.domain.repositories;


import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Bourse;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BourseRepository extends GenericRepository<Bourse> {

    @Query("SELECT b FROM Bourse b")
    Page<Bourse> findAllPageableBourse(Pageable pageable);
}
