package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.ModuleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.UeDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.MentionDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ModuleDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.SpecialiteDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.UeDtoResponse;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.models.Module;
import com.e221.pedagogieservice.domain.repositories.ModuleRepository;
import com.e221.pedagogieservice.domain.repositories.UeRepository;
import com.e221.pedagogieservice.domain.services.ModuleService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class ModuleServiceImp extends DefaultServiceImp<Module, ModuleDtoRequest, ModuleDtoResponse> implements ModuleService{

    private final ModuleRepository moduleRepository;
    private final UeRepository ueRepository;
    private final EntityManager entityManager;

    public ModuleServiceImp(ModuleRepository moduleRepository,
                            DefaultSpecification<Module> defaultSpecification,
                            CacheNameProvider cacheNameProvider,
                            CacheManager cacheManager, UeRepository ueRepository, EntityManager entityManager
    ) {
        super(moduleRepository, defaultSpecification, cacheNameProvider, cacheManager);
        this.moduleRepository = moduleRepository;
        this.ueRepository = ueRepository;
        this.entityManager = entityManager;
    }

    @Override
    protected Module reloadWithRelationships(Long id) {
        return moduleRepository.findByIdWithUes(id)
                .orElseThrow(() -> new EntityNotFoundException("Specialite not found id=" + id));
    }

    @Override
    protected List<ModuleDtoResponse> mapListToDto(List<Module> entities) {
        return entities.stream()
                .map(this::mapUeToDtoWithModule)
                .toList();
    }

    @Override
    protected ModuleDtoResponse mapToDto(Module module) {
        return mapUeToDtoWithModule(module);
    }

    @Override
    protected Module createRelationships(Module module, ModuleDtoRequest dto) {
        if (dto.getUes() != null && !dto.getUes().isEmpty()) {
            List<ModuleUE> links = dto.getUes().stream()
                    .map(ueDto -> {
                        UE ue = ueRepository.findById(ueDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("UE not found id=" + ueDto.getId()));
                        ModuleUE md = new ModuleUE();
                        md.setModule(module);
                        md.setUe(ue);
                        return md;
                    })
                    .toList();
            module.getModuleUES().addAll(links);
        }
        return module;
    }

    @Override
    protected Module updateRelationships(Module module, ModuleDtoRequest dto) {
        if (dto.getUes() != null) {          // only if caller included the field
            module.getModuleUES().clear();
            if (!dto.getUes().isEmpty()) {
                List<ModuleUE> links = dto.getUes().stream()
                        .map(nivDto -> {
                            UE ue = ueRepository.findById(nivDto.getId())
                                    .orElseThrow(() -> new EntityNotFoundException("UE not found id=" + nivDto.getId()));
                            ModuleUE md = new ModuleUE();
                            md.setModule(module);
                            md.setUe(ue);
                            return md;
                        })
                        .toList();
                module.getModuleUES().addAll(links);
            }
        }
        return module;
    }

    private ModuleDtoResponse mapUeToDtoWithModule(Module module) {
        ModuleDtoResponse dto = MapperService.mapToDtoResponse(module, ModuleDtoResponse.class);

        List<ModuleUE> links = module.getModuleUES();
        if (links == null || links.isEmpty()) {
            dto.setUes(Collections.emptyList());
            return dto;
        }

        List<UeDtoResponse> ues = links.stream()
                .map(ModuleUE::getUe)
                .filter(Objects::nonNull)
                .map(niv -> MapperService.mapToDtoResponse(niv, UeDtoResponse.class))
                .toList();

        dto.setUes(ues);
        return dto;
    }

    @Override
    public ModuleDtoResponse archive(Long id) {
        return null;
    }
}

