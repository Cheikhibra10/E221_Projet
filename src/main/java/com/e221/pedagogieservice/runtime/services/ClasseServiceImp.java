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
        if (dto.getNiveauId() != null) {
            Niveau niveau = DomainEntityHelper.findStrictById(niveauRepository, dto.getNiveauId(), Niveau.class);
            classe.setNiveau(niveau);
        } else {
            classe.setNiveau(null);
        }

        if (dto.getDomaineId() != null) {
            Domaine domaine = DomainEntityHelper.findStrictById(domaineRepository, dto.getDomaineId(), Domaine.class);
            classe.setDomaine(domaine);
        } else {
            classe.setDomaine(null);
        }

        if (dto.getSpecialiteId() != null) {
            Specialite specialite = DomainEntityHelper.findStrictById(specialiteRepository, dto.getSpecialiteId(), Specialite.class);
            classe.setSpecialite(specialite);
        } else {
            classe.setSpecialite(null);
        }


        return classe;
    }


    @Override
    protected Classe updateRelationships(Classe classe, ClasseDtoRequest dto) {

        if (dto.getNiveauId() != null) {
            Niveau niveau = DomainEntityHelper.findStrictById(niveauRepository, dto.getNiveauId(), Niveau.class);
            classe.setNiveau(niveau);
        } else {
            classe.setNiveau(null);
        }

        if (dto.getDomaineId() != null) {
            Domaine domaine = DomainEntityHelper.findStrictById(domaineRepository, dto.getDomaineId(), Domaine.class);
            classe.setDomaine(domaine);
        } else {
            classe.setDomaine(null);
        }

        if (dto.getSpecialiteId() != null) {
            Specialite specialite = DomainEntityHelper.findStrictById(specialiteRepository, dto.getSpecialiteId(), Specialite.class);
            classe.setSpecialite(specialite);
        } else {
            classe.setSpecialite(null);
        }

        return classe;
    }
}

