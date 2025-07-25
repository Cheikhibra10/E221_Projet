package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.models.NiveauSpecialite;
import com.e221.pedagogieservice.domain.models.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NiveauSpecialiteRepository extends JpaRepository<NiveauSpecialite, Long> {
    Optional<NiveauSpecialite> findByNiveauAndSpecialiteAndArchiveFalse(Niveau niveau, Specialite specialite);

}
