package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.SousClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.SousClasseDtoResponse;
import com.e221.pedagogieservice.domain.models.Classe;
import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.models.Semestre;
import com.e221.pedagogieservice.domain.models.SousClasse;
import com.e221.pedagogieservice.domain.repositories.ClasseRepository;
import com.e221.pedagogieservice.domain.repositories.SemestreRepository;
import com.e221.pedagogieservice.domain.repositories.SousClasseRepository;
import com.e221.pedagogieservice.domain.services.SousClasseService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;


@Service
@Transactional
@Slf4j
public class SousClasseServiceImp
        extends DefaultServiceImp<SousClasse, SousClasseDtoRequest, SousClasseDtoResponse>
        implements SousClasseService {

    private final SousClasseRepository repository;
    private final ClasseRepository classeRepository;
    private final SemestreRepository semestreRepository;
    private final EntityManager entityManager;

    public SousClasseServiceImp(
            SousClasseRepository repository,
            DefaultSpecification<SousClasse> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager entityManager, ClasseRepository classeRepository, SemestreRepository semestreRepository, EntityManager entityManager1
    ) {
        super(repository, defaultSpecification, cacheNameProvider, entityManager);
        this.repository = repository;
        this.classeRepository = classeRepository;
        this.semestreRepository = semestreRepository;
        this.entityManager = entityManager1;
    }

    @Override
    protected SousClasse createRelationships(SousClasse sousClasse, SousClasseDtoRequest dto) {
        if (dto.getClasseId() != null) {
            Classe classe = DomainEntityHelper.findStrictById(classeRepository, dto.getClasseId(), Classe.class);
            sousClasse.setClasse(classe);
        } else {
            sousClasse.setClasse(null);
        } if (dto.getSemestreId() != null) {
            Semestre semestre = DomainEntityHelper.findStrictById(semestreRepository, dto.getSemestreId(), Semestre.class);
            sousClasse.setSemestre(semestre);
        } else {
            sousClasse.setSemestre(null);
        }
        return sousClasse;
    }

    @Override
    protected SousClasse updateRelationships(SousClasse sousClasse, SousClasseDtoRequest dto) {
        if (dto.getClasseId() != null) {
            if(dto.getClasseId() > 0){
                Classe classe = DomainEntityHelper.findStrictById(classeRepository, dto.getClasseId(), Classe.class);
                sousClasse.setClasse(classe);
            } else {
                sousClasse.setClasse(null);
            }

        }
        if (dto.getSemestreId() != null) {
            if(dto.getSemestreId() > 0){
                Semestre semestre = DomainEntityHelper.findStrictById(semestreRepository, dto.getSemestreId(), Semestre.class);
                sousClasse.setSemestre(semestre);
            } else {
                sousClasse.setSemestre(null);
            }

        }
        return sousClasse;
    }

    // 🔥 Eviction lors de l'archivage
    @Override
    @Caching(evict = {
            @CacheEvict(value = "sousclasseList", allEntries = true),
            @CacheEvict(value = "sousclasse", key = "#id")
    })
    public SousClasseDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(entity), SousClasseDtoResponse.class);
    }
}
