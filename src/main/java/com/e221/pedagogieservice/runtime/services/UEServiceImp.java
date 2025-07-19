package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.UeDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.*;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.models.Module;
import com.e221.pedagogieservice.domain.repositories.MentionRepository;
import com.e221.pedagogieservice.domain.repositories.UeRepository;
import com.e221.pedagogieservice.domain.services.UeService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class UEServiceImp
        extends DefaultServiceImp<UE, UeDtoRequest, UeDtoResponse>
        implements UeService {

    private final UeRepository ueRepository;
    private final MentionRepository mentionRepository;
    private final EntityManager entityManager;
    private final CacheNameProvider cacheNameProvider;
    private final CacheManager cacheManager;

    public UEServiceImp(
            UeRepository ueRepository,
            DefaultSpecification<UE> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager,
            MentionRepository mentionRepository,
            EntityManager entityManager
    ) {
        super(ueRepository, defaultSpecification, cacheNameProvider, cacheManager);
        this.ueRepository = ueRepository;
        this.mentionRepository = mentionRepository;
        this.entityManager = entityManager;
        this.cacheNameProvider = cacheNameProvider;
        this.cacheManager = cacheManager;
    }

    @Override
    protected UE reloadWithRelationships(Long id) {
        return ueRepository.findByIdWithMentions(id)
                .orElseThrow(() -> new EntityNotFoundException("Specialite not found id=" + id));
    }

    @Override
    protected List<UeDtoResponse> mapListToDto(List<UE> entities) {
        return entities.stream()
                .map(this::mapUeToDtoWithMentions)
                .toList();
    }
    @Override
    protected UeDtoResponse mapToDto(UE ue) {
        return mapUeToDtoWithMentions(ue);
    }

    @Override
    protected UE createRelationships(UE ue, UeDtoRequest dto) {
        if (dto.getMentions() != null && !dto.getMentions().isEmpty()) {
            List<MentionUE> links = dto.getMentions().stream()
                    .map(mentionDto -> {
                        Mention mention = mentionRepository.findById(mentionDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Mention not found id=" + mentionDto.getId()));
                        MentionUE mentionUE = new MentionUE();
                        mentionUE.setUe(ue);
                        mentionUE.setMention(mention);
                        return mentionUE;
                    })
                    .toList();
            ue.getMentionUES().addAll(links);
        }
        return ue;
    }

    @Override
    protected UE updateRelationships(UE ue, UeDtoRequest dto) {
        if (dto.getMentions() != null) {          // only if caller included the field
            ue.getMentionUES().clear();
            if (!dto.getMentions().isEmpty()) {
                List<MentionUE> links = dto.getMentions().stream()
                        .map(nivDto -> {
                            Mention mention = mentionRepository.findById(nivDto.getId())
                                    .orElseThrow(() -> new EntityNotFoundException("Mention not found id=" + nivDto.getId()));
                            MentionUE mentionUE = new MentionUE();
                            mentionUE.setUe(ue);
                            mentionUE.setMention(mention);
                            return mentionUE;
                        })
                        .toList();
                ue.getMentionUES().addAll(links);
            }
        }

        return ue;
    }




    private UeDtoResponse mapUeToDtoWithMentions(UE ue) {
        UeDtoResponse dto = MapperService.mapToDtoResponse(ue, UeDtoResponse.class);

        List<MentionUE> links = ue.getMentionUES();
        if (links == null || links.isEmpty()) {
            dto.setMentions(Collections.emptyList());
            return dto;
        }

        List<MentionDtoResponse> mentions = links.stream()
                .map(MentionUE::getMention)
                .filter(Objects::nonNull)
                .map(niv -> MapperService.mapToDtoResponse(niv, MentionDtoResponse.class))
                .toList();

        dto.setMentions(mentions);
        return dto;
    }

    // 🔥 Eviction lors de l'archivage
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ueList", allEntries = true),
            @CacheEvict(value = "ue", key = "#id")
    })
    public UeDtoResponse archive(Long id) {
        var ue = getEntityById(id);
        ue.setArchive(true);
        return MapperService.mapToDtoResponse(ueRepository.save(ue), UeDtoResponse.class);
    }
}

