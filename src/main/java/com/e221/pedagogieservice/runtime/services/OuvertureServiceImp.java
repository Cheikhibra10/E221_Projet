package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.OuvertureDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.OuvertureDtoResponse;
import com.e221.pedagogieservice.domain.models.AnneeScolaire;
import com.e221.pedagogieservice.domain.models.Cycle;
import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.models.Ouverture;
import com.e221.pedagogieservice.domain.repositories.AnneeScolaireRepository;
import com.e221.pedagogieservice.domain.repositories.OuvertureRepository;
import com.e221.pedagogieservice.domain.services.OuvertureService;
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
public class OuvertureServiceImp
        extends DefaultServiceImp<Ouverture, OuvertureDtoRequest, OuvertureDtoResponse>
        implements OuvertureService {

    private final OuvertureRepository ouvertureRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;
    private final EntityManager entityManager;

    public OuvertureServiceImp(
            OuvertureRepository ouvertureRepository,
            DefaultSpecification<Ouverture> defaultSpecification,
            AnneeScolaireRepository anneeScolaireRepository,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager, EntityManager entityManager
    ) {
        super(ouvertureRepository, defaultSpecification, cacheNameProvider, cacheManager);
        this.ouvertureRepository = ouvertureRepository;
        this.anneeScolaireRepository = anneeScolaireRepository;
        this.entityManager = entityManager;
    }




    // 🔥 Archive → éviction liste + item
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ouvertureList", allEntries = true),
            @CacheEvict(value = "ouverture", key = "#id")
    })
    public OuvertureDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(ouvertureRepository.save(entity), OuvertureDtoResponse.class);
    }

    @Override
    protected Ouverture createRelationships(Ouverture ouverture, OuvertureDtoRequest dto) {
        if (dto.getAnneeScolaireId() != null) {
            if (dto.getAnneeScolaireId() > 0) {
            AnneeScolaire anneeScolaire = DomainEntityHelper.findStrictById(anneeScolaireRepository, dto.getAnneeScolaireId(), AnneeScolaire.class);
            ouverture.setAnneeScolaire(anneeScolaire);
        } else {
            ouverture.setAnneeScolaire(null);
        }
        }
        return ouverture;
    }
    // 🔗 Relations
    @Override
    protected Ouverture updateRelationships(Ouverture ouverture, OuvertureDtoRequest dto) {

        if (dto.getAnneeScolaireId() != null) {   // champ présent dans la requête
            if (dto.getAnneeScolaireId() > 0) {
                AnneeScolaire anneeScolaire = DomainEntityHelper.findStrictById(
                        anneeScolaireRepository,
                        dto.getAnneeScolaireId(),
                        AnneeScolaire.class
                );
                ouverture.setAnneeScolaire(anneeScolaire);
            } else {
                // id <= 0 → détachement
                ouverture.setAnneeScolaire(null);
            }
        }
        // sinon: champ absent → on garde l'ancienne relation

        return ouverture;
    }

}
