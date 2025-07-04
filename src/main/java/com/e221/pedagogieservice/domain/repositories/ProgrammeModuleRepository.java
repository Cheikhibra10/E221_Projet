package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgrammeModuleRepository extends JpaRepository<ProgrammeModule, Long> {
    Optional<List<ProgrammeModule>> findAllByArchiveFalse();

    @Query("select pm from ProgrammeModule pm where pm.archive=false and pm.programmeUE.id=:programmeUEId")
    Optional<List<ProgrammeModule>> findAllByProgrammeUEAndArchiveFalse(@Param("programmeUEId") Long programmeUEId);

    Optional<List<ProgrammeModule>> findAllByModuleAndArchiveFalse(Module module);

    Optional<List<ProgrammeModule>> findAllByModuleAndProgrammeUEAndArchiveFalse(java.lang.Module module, ProgrammeUE programmeUE);

    Optional<List<ProgrammeModule>> findAllByArchiveFalseAndProgrammeUE_Referentiel(Referentiel referentiel);

    Optional<List<ProgrammeModule>> findAllByArchiveFalseAndProgrammeUE_ReferentielAndProgrammeUE_Semestre(Referentiel referentiel, Semestre semestre);
}
