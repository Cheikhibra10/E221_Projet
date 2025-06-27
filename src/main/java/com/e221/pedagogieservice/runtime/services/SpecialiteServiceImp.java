package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.SpecialiteDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.SpecialiteDtoResponse;
import com.e221.pedagogieservice.domain.models.Mention;
import com.e221.pedagogieservice.domain.models.Specialite;
import com.e221.pedagogieservice.domain.repositories.MentionRepository;
import com.e221.pedagogieservice.domain.repositories.SpecialiteRepository;
import com.e221.pedagogieservice.domain.services.SpecialiteService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Transactional
@Slf4j
public class SpecialiteServiceImp extends DefaultServiceImp<Specialite, SpecialiteDtoRequest, SpecialiteDtoResponse> implements SpecialiteService {
    private final SpecialiteRepository repository;
    private final MentionRepository mentionRepository;
    public SpecialiteServiceImp(SpecialiteRepository repository, DefaultSpecification<Specialite> defaultSpecification, MentionRepository mentionRepository) {
        super(repository, defaultSpecification);
        this.repository = repository;
        this.mentionRepository = mentionRepository;
    }


    @Override
    public SpecialiteDtoResponse archive(Long id) {
        var specialite = getEntityById(id);
        specialite.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(specialite), SpecialiteDtoResponse.class);
    }

    @Override
    protected Specialite updateRelationships(Specialite specialite, SpecialiteDtoRequest dto) {
        if (dto.getMention() != null){

            Mention mention = DomainEntityHelper.findOrUpdate(
                    mentionRepository,
                    dto.getMention(),
                    Mention.class,
                        existingMention-> existingMention.getLibelle().equalsIgnoreCase(dto.getMention().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            specialite.setMention(mention);
        }else{
            specialite.setMention(null);
        }
        return specialite;
    }

}
