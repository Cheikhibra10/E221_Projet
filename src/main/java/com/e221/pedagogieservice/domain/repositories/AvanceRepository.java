package com.e221.pedagogieservice.domain.repositories;


import com.e221.pedagogieservice.domain.models.Avance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvanceRepository extends JpaRepository<Avance, Long> {
    
}
