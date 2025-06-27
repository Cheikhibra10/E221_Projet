package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.MentionDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.MentionDtoResponse;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.models.Mention;
import com.e221.pedagogieservice.domain.repositories.DomaineRepository;
import com.e221.pedagogieservice.domain.repositories.MentionRepository;
import com.e221.pedagogieservice.domain.services.MentionService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.domain.utils.LoggingUtil;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.e221.pedagogieservice.domain.utils.DomainEntityHelper.findOrUpdate;
import static java.lang.System.out;

@Service
@Transactional
@Slf4j
public class MentionServiceImp extends DefaultServiceImp<Mention, MentionDtoRequest, MentionDtoResponse> implements MentionService {
    private final MentionRepository repository;

    public MentionServiceImp(MentionRepository repository, DefaultSpecification<Mention> defaultSpecification, DomaineRepository domaineRepository) {
        super(repository, defaultSpecification);
        this.repository = repository;
        this.domaineRepository = domaineRepository;
    }

    private final DomaineRepository domaineRepository;

    @Override
    public MentionDtoResponse archive(Long id) {
        var mention = getEntityById(id);
        mention.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(mention), MentionDtoResponse.class);
    }

    @Override
    protected Mention createRelationships(Mention mention, MentionDtoRequest dto) {
        if (dto.getDomaine() != null) {
            Domaine domaine = DomainEntityHelper.findOrCreate(domaineRepository, dto.getDomaine(), Domaine.class,
                    existingDomaine ->
                            existingDomaine.getLibelle().equals(dto.getDomaine().getLibelle())
            );
            mention.setDomaine(domaine);
        }
        return mention;
    }

    @Override
    protected Mention updateRelationships(Mention mention, MentionDtoRequest dto) {
        if (dto.getDomaine() != null) {
            Domaine domaine = findOrUpdate(
                    domaineRepository,
                    dto.getDomaine(),
                    Domaine.class,
                    existing -> existing.getLibelle().equalsIgnoreCase(dto.getDomaine().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            mention.setDomaine(domaine);
        } else {
            mention.setDomaine(null);
        }
        return mention;
    }

}
