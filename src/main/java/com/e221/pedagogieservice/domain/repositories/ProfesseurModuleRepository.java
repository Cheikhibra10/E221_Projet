package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Professeur;
import com.e221.pedagogieservice.domain.models.ProfesseurModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesseurModuleRepository extends JpaRepository<ProfesseurModule, Long> {

}
