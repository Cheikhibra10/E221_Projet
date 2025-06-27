package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.AnneeScolaireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.AnneeScolaireDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.AnneeScolaire;
import com.e221.pedagogieservice.domain.repositories.AnneeScolaireRepository;
import com.e221.pedagogieservice.domain.services.AnneeScolaireService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class AnneeScolaireServiceImp extends DefaultServiceImp<AnneeScolaire, AnneeScolaireDtoRequest, AnneeScolaireDtoResponse> implements AnneeScolaireService {
    private final AnneeScolaireRepository anneeScolaireRepository;

    public AnneeScolaireServiceImp(AnneeScolaireRepository anneeScolaireRepository, DefaultSpecification<AnneeScolaire> defaultSpecification) {
        super(anneeScolaireRepository, defaultSpecification);
        this.anneeScolaireRepository = anneeScolaireRepository;
    }


    @Override
    public AnneeScolaireDtoResponse archive(Long id) {
        var annee = getEntityById(id);
        annee.setArchive(true);
        return MapperService.mapToDtoResponse(anneeScolaireRepository.save(annee), AnneeScolaireDtoResponse.class);
    }



}
