package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.ProgrammeUE;
import com.e221.pedagogieservice.domain.models.Referentiel;
import com.e221.pedagogieservice.domain.models.Semestre;
import com.e221.pedagogieservice.domain.models.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgrammeUERepository extends JpaRepository<ProgrammeUE, Long> {

}
