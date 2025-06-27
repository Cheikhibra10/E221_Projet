package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.models.Semestre;
import com.e221.pedagogieservice.domain.models.SemestreNiveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SemestreNiveauRepository extends JpaRepository<SemestreNiveau, Long> {

}
