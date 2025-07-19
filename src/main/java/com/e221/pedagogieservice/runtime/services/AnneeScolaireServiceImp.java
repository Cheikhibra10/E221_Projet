package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.cheikh.commun.core.PageResponse;
import com.e221.pedagogieservice.domain.dtos.requests.AnneeScolaireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.AnneeScolaireDtoResponse;
import com.e221.pedagogieservice.domain.models.AnneeScolaire;
import com.e221.pedagogieservice.domain.models.Ouverture;
import com.e221.pedagogieservice.domain.models.Statut;
import com.e221.pedagogieservice.domain.repositories.OuvertureRepository;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import jakarta.persistence.EntityManager;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.e221.pedagogieservice.domain.repositories.AnneeScolaireRepository;
import com.e221.pedagogieservice.domain.services.AnneeScolaireService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class AnneeScolaireServiceImp
        extends DefaultServiceImp<AnneeScolaire, AnneeScolaireDtoRequest, AnneeScolaireDtoResponse>
        implements AnneeScolaireService {

    private final AnneeScolaireRepository anneeScolaireRepository;
    private final OuvertureRepository ouvertureRepository;
    private final CacheManager cacheManager;
    public AnneeScolaireServiceImp(
            AnneeScolaireRepository anneeScolaireRepository,
            DefaultSpecification<AnneeScolaire> spec,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager, OuvertureRepository ouvertureRepository, CacheManager cacheManager1
    ) {
        super(anneeScolaireRepository, spec,cacheNameProvider, cacheManager);
        this.anneeScolaireRepository = anneeScolaireRepository;
        this.ouvertureRepository = ouvertureRepository;
        this.cacheManager = cacheManager;
    }


    // Archive avec éviction
    @Override
    @Caching(evict = {
            @CacheEvict(value = "anneescolaireList", allEntries = true),
            @CacheEvict(value = "anneescolaire", key = "#id")
    })
    public AnneeScolaireDtoResponse archive(Long id) {
        var annee = getEntityById(id);
        annee.setArchive(true);
        return MapperService.mapToDtoResponse(anneeScolaireRepository.save(annee), AnneeScolaireDtoResponse.class);
    }

    @Override
    @Transactional
    public AnneeScolaireDtoResponse patchFields(Long id, Map<String, Object> fields) {
        // 1. Get the requested new statut if present
        Statut newStatut = null;
        if (fields.containsKey("statut") && fields.get("statut") != null) {
            newStatut = Statut.valueOf(fields.get("statut").toString().trim());
        }

        // 2. Apply generic patch
        AnneeScolaireDtoResponse dto = super.patchFields(id, fields);

        // 3. If statut became Cloturer => batch close all ouvertures
        if (newStatut == Statut.Cloturer) {
            int updatedCount = ouvertureRepository.closeAllByAnnee(id);
            log.info("🔒 {} ouvertures closed for AnneeScolaire {}", updatedCount, id);

            // Optional: evict caches for Ouverture
            if (cacheManager.getCache("ouvertureList") != null) {
                cacheManager.getCache("ouvertureList").clear();
            }
            if (cacheManager.getCache("ouverture") != null) {
                // Evict by IDs if needed (we don't know them here, so just clear all)
                cacheManager.getCache("ouverture").clear();
            }
        }
        if (newStatut == Statut.En_Cours) {
            int updatedCount = ouvertureRepository.openAllByAnnee(id);
            log.info("🔒 {} ouvertures opened for AnneeScolaire {}", updatedCount, id);

            // Optional: evict caches for Ouverture
            if (cacheManager.getCache("ouvertureList") != null) {
                cacheManager.getCache("ouvertureList").clear();
            }
            if (cacheManager.getCache("ouverture") != null) {
                // Evict by IDs if needed (we don't know them here, so just clear all)
                cacheManager.getCache("ouverture").clear();
            }
        }

        return dto;
    }


}
