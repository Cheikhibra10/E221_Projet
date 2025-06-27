package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.PlanificationCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanificationCoursRepository extends JpaRepository<PlanificationCours, Long> {
}
