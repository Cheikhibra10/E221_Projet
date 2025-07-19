package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.MentionDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.MentionDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.SemestreDtoResponse;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.repositories.SemestreRepository;
import com.e221.pedagogieservice.domain.services.SemestreService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@Slf4j
public class SemestreServiceImp
        extends DefaultServiceImp<Semestre, SemestreDtoRequest, SemestreDtoResponse>
        implements SemestreService {

    private final SemestreRepository semestreRepository;
    private final NiveauRepository niveauRepository;
    private final EntityManager entityManager;

    public SemestreServiceImp(
            SemestreRepository semestreRepository,
            DefaultSpecification<Semestre> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager, NiveauRepository niveauRepository, EntityManager entityManager
    ) {
        super(semestreRepository, defaultSpecification, cacheNameProvider, cacheManager);
        this.semestreRepository = semestreRepository;
        this.niveauRepository = niveauRepository;
        this.entityManager = entityManager;
    }

    @Override
    protected Semestre createRelationships(Semestre semestre, SemestreDtoRequest dto) {
        if (dto.getNiveau() != null) {
            Niveau niveau = DomainEntityHelper.findOrCreateStrict(
                    niveauRepository,
                    dto.getNiveau(),
                    Niveau.class,
                    root -> root.get("libelle").in(dto.getNiveau().getLibelle()),
                    MapperService::patchEntityFromDto,
                    entityManager
            );
            semestre.setNiveau(niveau);
        }
        return semestre;
    }

    @Override
    protected Semestre updateRelationships(Semestre semestre, SemestreDtoRequest dto) {
        if (dto.getNiveau() != null) {
            Niveau niveau = DomainEntityHelper.findOrUpdate(
                    niveauRepository,
                    dto.getNiveau(),
                    Niveau.class,
                    existingNiveau-> existingNiveau.getLibelle().equalsIgnoreCase(dto.getNiveau().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            semestre.setNiveau(niveau);
        }else{
            semestre.setNiveau(null);
        }
        return semestre;
    }

    // Eviction lors de l'archivage
    @Override
    @Caching(evict = {
            @CacheEvict(value = "semestreList", allEntries = true),
            @CacheEvict(value = "semestre", key = "#id")
    })
    public SemestreDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(semestreRepository.save(entity), SemestreDtoResponse.class);
    }
}

