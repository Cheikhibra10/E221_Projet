package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Contrat;
import com.e221.pedagogieservice.domain.models.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
}
