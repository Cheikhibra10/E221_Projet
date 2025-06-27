package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.models.Referentiel;
import com.e221.pedagogieservice.domain.models.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferentielRepository extends JpaRepository<Referentiel, Long> {

}
