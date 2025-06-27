package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.OuvertureDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SpecialiteDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.OuvertureDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.AnneeScolaire;
import com.e221.pedagogieservice.domain.models.Mention;
import com.e221.pedagogieservice.domain.models.Ouverture;
import com.e221.pedagogieservice.domain.models.Specialite;
import com.e221.pedagogieservice.domain.repositories.AnneeScolaireRepository;
import com.e221.pedagogieservice.domain.repositories.OuvertureRepository;
import com.e221.pedagogieservice.domain.services.OuvertureService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
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
public class OuvertureServiceImp extends DefaultServiceImp<Ouverture, OuvertureDtoRequest, OuvertureDtoResponse> implements OuvertureService {
    private final OuvertureRepository ouvertureRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;

    public OuvertureServiceImp(OuvertureRepository ouvertureRepository, DefaultSpecification<Ouverture> defaultSpecification, AnneeScolaireRepository anneeScolaireRepository) {
        super(ouvertureRepository, defaultSpecification);
        this.ouvertureRepository = ouvertureRepository;
        this.anneeScolaireRepository = anneeScolaireRepository;
    }

    @Override
    protected Ouverture updateRelationships(Ouverture ouverture, OuvertureDtoRequest dto) {
        if (dto.getAnneeScolaire() != null){

            AnneeScolaire anneeScolaire = DomainEntityHelper.findOrUpdate(
                    anneeScolaireRepository,
                    dto.getAnneeScolaire(),
                    AnneeScolaire.class,
                    existingMention-> existingMention.getLibelle().equalsIgnoreCase(dto.getAnneeScolaire().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            ouverture.setAnneeScolaire(anneeScolaire);
        }else{
            ouverture.setAnneeScolaire(null);
        }
        return ouverture;
    }


}
