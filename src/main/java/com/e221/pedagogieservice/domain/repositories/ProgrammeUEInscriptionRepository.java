package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Inscription;
import com.e221.pedagogieservice.domain.models.ProgrammeUE;
import com.e221.pedagogieservice.domain.models.ProgrammeUEInscription;
import com.e221.pedagogieservice.domain.models.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgrammeUEInscriptionRepository extends JpaRepository<ProgrammeUEInscription, Long> {

}
