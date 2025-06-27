package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, Long> {
}
