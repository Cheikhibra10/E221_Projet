package com.e221.pedagogieservice.runtime.services;


import com.cheikh.commun.core.GenericRepository;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.CalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.EvenementDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.OuvertureDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CalendrierDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.EvenementDtoResponse;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.services.CalendrierService;
import com.e221.pedagogieservice.domain.services.EvenementService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class CalendrierServiceImp extends DefaultServiceImp<Calendrier, CalendrierDtoRequest, CalendrierDtoResponse> implements CalendrierService {

    private final NiveauRepository niveauRepository;
    public CalendrierServiceImp(GenericRepository<Calendrier> repository, DefaultSpecification<Calendrier> defaultSpecification, NiveauRepository niveauRepository) {
        super(repository, defaultSpecification);
        this.niveauRepository = niveauRepository;
    }

    @Override
    protected Calendrier updateRelationships(Calendrier calendrier, CalendrierDtoRequest dto) {
        if (dto.getNiveau() != null){
            Niveau niveau = DomainEntityHelper.findOrUpdate(
                    niveauRepository,
                    dto.getNiveau(),
                    Niveau.class,
                    existing-> existing.getLibelle().equalsIgnoreCase(dto.getNiveau().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            calendrier.setNiveau(niveau);
        }else{
            calendrier.setNiveau(null);
        }
        return calendrier;
    }
}
