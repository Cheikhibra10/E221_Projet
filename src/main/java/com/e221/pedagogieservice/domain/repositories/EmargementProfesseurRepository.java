package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.EmargementProfesseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmargementProfesseurRepository extends JpaRepository<EmargementProfesseur, Long> {
}
