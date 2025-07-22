package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.DocumentDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.ModuleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.*;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.models.Module;
import com.e221.pedagogieservice.domain.repositories.DocumentRepository;
import com.e221.pedagogieservice.domain.repositories.ModuleRepository;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.repositories.UeRepository;
import com.e221.pedagogieservice.domain.services.DocumentService;
import com.e221.pedagogieservice.domain.services.ModuleService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class DocumentServiceImp extends DefaultServiceImp<Document, DocumentDtoRequest, DocumentDtoResponse> implements DocumentService {

    private final DocumentRepository documentRepository;
    private final NiveauRepository niveauRepository;
    private final EntityManager entityManager;
    private final CacheNameProvider cacheNameProvider;
    private final CacheManager cacheManager;
    public DocumentServiceImp(DocumentRepository documentRepository,
                              DefaultSpecification<Document> defaultSpecification,
                              CacheNameProvider cacheNameProvider,
                              CacheManager cacheManager, NiveauRepository niveauRepository, EntityManager entityManager
    ) {
        super(documentRepository, defaultSpecification, cacheNameProvider, cacheManager);
        this.documentRepository = documentRepository;
        this.niveauRepository = niveauRepository;
        this.entityManager = entityManager;
        this.cacheNameProvider = cacheNameProvider;
        this.cacheManager = cacheManager;
    }

    @Override
    protected Document reloadWithRelationships(Long id) {
        return documentRepository.findByIdWithNiveaux(id)
                .orElseThrow(() -> new EntityNotFoundException("Specialite not found id=" + id));
    }

    @Override
    protected List<DocumentDtoResponse> mapListToDto(List<Document> entities) {
        return entities.stream()
                .map(this::mapDocumentToDtoWithNiveaux)
                .toList();
    }

    @Override
    protected DocumentDtoResponse mapToDto(Document document) {
        return mapDocumentToDtoWithNiveaux(document);
    }


    @Override
    protected Document createRelationships(Document document, DocumentDtoRequest dto) {
        if (dto.getNiveaux() != null && !dto.getNiveaux().isEmpty()) {
            List<DocumentParNiveau> links = dto.getNiveaux().stream()
                    .map(niveauId -> {
                        Niveau niveau = niveauRepository.findById(niveauId)
                                .orElseThrow(() -> new EntityNotFoundException("niveau not found id=" + niveauId));
                        DocumentParNiveau ns = new DocumentParNiveau();
                        ns.setDocument(document);
                        ns.setNiveau(niveau);
                        return ns;
                    })
                    .toList();
            document.getDocumentParNiveaux().addAll(links);
        }
        return document;
    }

    @Override
    protected Document updateRelationships(Document doc, DocumentDtoRequest dto) {

        if (dto.getNiveaux() != null) {          // only if caller included the field
            doc.getDocumentParNiveaux().clear();
            if (!dto.getNiveaux().isEmpty()) {
                List<DocumentParNiveau> links = dto.getNiveaux().stream()
                        .map(niveauId -> {
                            Niveau niveau = niveauRepository.findById(niveauId)
                                    .orElseThrow(() -> new EntityNotFoundException("Niveau not found id=" + niveauId));
                            DocumentParNiveau l = new DocumentParNiveau();
                            l.setDocument(doc);
                            l.setNiveau(niveau);
                            return l;
                        })
                        .toList();
                doc.getDocumentParNiveaux().addAll(links);
            }
        }

        return doc;
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = "documentList", allEntries = true),
            @CacheEvict(value = "document", key = "#id")
    })
    public DocumentDtoResponse archive(Long id) {
        var document = getEntityById(id);
        document.setArchive(true);
        return MapperService.mapToDtoResponse(documentRepository.save(document), DocumentDtoResponse.class);    }

    private DocumentDtoResponse mapDocumentToDtoWithNiveaux(Document d) {
        DocumentDtoResponse dto = MapperService.mapToDtoResponse(d, DocumentDtoResponse.class);

        List<DocumentParNiveau> links = d.getDocumentParNiveaux();
        if (links == null || links.isEmpty()) {
            dto.setNiveaux(Collections.emptyList());
            return dto;
        }

        List<NiveauDtoResponse> niveaux = links.stream()
                .map(DocumentParNiveau::getNiveau)
                .filter(Objects::nonNull)
                .map(niv -> MapperService.mapToDtoResponse(niv, NiveauDtoResponse.class))
                .toList();

        dto.setNiveaux(niveaux);
        return dto;
    }

}