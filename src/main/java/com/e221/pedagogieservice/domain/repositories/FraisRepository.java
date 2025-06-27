package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Frais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraisRepository extends JpaRepository<Frais, Long> {
    
}
