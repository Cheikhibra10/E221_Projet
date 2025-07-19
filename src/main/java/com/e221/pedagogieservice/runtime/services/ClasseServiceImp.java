package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.ClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SpecialiteDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ClasseDtoResponse;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.*;
import com.e221.pedagogieservice.domain.services.ClasseService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
public class ClasseServiceImp extends DefaultServiceImp<Classe, ClasseDtoRequest, ClasseDtoResponse> implements ClasseService {

    private final ClasseRepository repository;
    private final DomaineRepository domaineRepository;
    private final NiveauRepository niveauRepository;
    private final SpecialiteRepository specialiteRepository;
    private final EntityManager entityManager;

    public ClasseServiceImp(ClasseRepository repository,
                            DefaultSpecification<Classe> defaultSpecification,
                            CacheNameProvider cacheNameProvider,
                            CacheManager cacheManager,
                            DomaineRepository domaineRepository,
                            NiveauRepository niveauRepository,
                            SpecialiteRepository specialiteRepository, EntityManager entityManager) {
        super(repository, defaultSpecification, cacheNameProvider, cacheManager);
        this.repository = repository;
        this.domaineRepository = domaineRepository;
        this.niveauRepository = niveauRepository;
        this.specialiteRepository = specialiteRepository;
        this.entityManager = entityManager;
    }


    @Override
    public ClasseDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(entity), ClasseDtoResponse.class);
    }

    @Override
    protected Classe createRelationships(Classe classe, ClasseDtoRequest dto) {
        if (dto.getSpecialite() != null) {
            Specialite specialite = DomainEntityHelper.findOrCreateStrict(
                    specialiteRepository,
                    dto.getSpecialite(),
                    Specialite.class,
                    root -> root.get("libelle").in(dto.getSpecialite().getLibelle()), //  dynamic predicate
                    MapperService::patchEntityFromDto,
                    entityManager
            );
            classe.setSpecialite(specialite);
        }

        if (dto.getDomaine() != null) {
            Domaine domaine = DomainEntityHelper.findOrCreateStrict(
                    domaineRepository,
                    dto.getDomaine(),
                    Domaine.class,
                    root -> root.get("libelle").in(dto.getDomaine().getLibelle()),
                    MapperService::patchEntityFromDto,
                    entityManager
            );
            classe.setDomaine(domaine);
        }

        if (dto.getNiveau() != null) {
            Niveau niveau = DomainEntityHelper.findOrCreateStrict(
                    niveauRepository,
                    dto.getNiveau(),
                    Niveau.class,
                    root -> root.get("libelle").in(dto.getNiveau().getLibelle()),
                    MapperService::patchEntityFromDto,
                    entityManager
            );
            classe.setNiveau(niveau);
        }


        return classe;
    }


    @Override
    protected Classe updateRelationships(Classe classe, ClasseDtoRequest dto) {

        if (dto.getDomaine() != null) {
            Domaine domaine = DomainEntityHelper.findOrUpdate(
                    domaineRepository,
                    dto.getDomaine(),
                    Domaine.class,
                    existing -> existing.getLibelle().equalsIgnoreCase(dto.getDomaine().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            classe.setDomaine(domaine);
        }else{
            classe.setDomaine(null);
        }

        if (dto.getNiveau() != null) {
            Niveau niveau = DomainEntityHelper.findOrUpdate(
                    niveauRepository,
                    dto.getNiveau(),
                    Niveau.class,
                    existing -> existing.getLibelle().equalsIgnoreCase(dto.getNiveau().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            classe.setNiveau(niveau);
        }else{
            classe.setNiveau(null);
        }

        if (dto.getSpecialite() != null) {
            Specialite specialite = DomainEntityHelper.findOrUpdate(
                    specialiteRepository,
                    dto.getSpecialite(),
                    Specialite.class,
                    existing ->
                            existing.getLibelle().equalsIgnoreCase(dto.getSpecialite().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            classe.setSpecialite(specialite);
        }else{
            classe.setSpecialite(null);
        }

        return classe;
    }
}

