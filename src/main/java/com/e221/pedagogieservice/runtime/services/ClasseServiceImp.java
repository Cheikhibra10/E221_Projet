package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.constants.ErrorsMessages;
import com.e221.pedagogieservice.domain.dtos.requests.ClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SpecialiteDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ClasseDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.ClasseRepository;
import com.e221.pedagogieservice.domain.repositories.HoraireRepository;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.repositories.SpecialiteRepository;
import com.e221.pedagogieservice.domain.services.ClasseService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
public class ClasseServiceImp extends DefaultServiceImp<Classe, ClasseDtoRequest, ClasseDtoResponse> implements ClasseService {
    private final ClasseRepository repository;
    private final HoraireRepository horaireRepository;
    private final NiveauRepository niveauRepository;
    private final SpecialiteRepository specialiteRepository;
    public ClasseServiceImp(ClasseRepository repository, DefaultSpecification<Classe> defaultSpecification, HoraireRepository horaireRepository, NiveauRepository niveauRepository, SpecialiteRepository specialiteRepository) {
        super(repository, defaultSpecification);
        this.repository = repository;
        this.horaireRepository = horaireRepository;
        this.niveauRepository = niveauRepository;
        this.specialiteRepository = specialiteRepository;
    }

    @Override
    public ClasseDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(entity), ClasseDtoResponse.class);
    }

    @Override
    protected Classe updateRelationships(Classe classe, ClasseDtoRequest dto) {

        if (dto.getHoraire() != null) {
            Horaire horaire = DomainEntityHelper.findOrUpdate(
                    horaireRepository,
                    dto.getHoraire(),
                    Horaire.class,
                    existing -> existing.getLibelle().equalsIgnoreCase(dto.getHoraire().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            classe.setHoraire(horaire);
        } else {
            classe.setHoraire(null);
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
        } else {
            classe.setNiveau(null);
        }

        if (dto.getSpecialite() != null) {
            Specialite specialite = DomainEntityHelper.findOrUpdate(
                    specialiteRepository,
                    dto.getSpecialite(),
                    Specialite.class,
                    existing ->
                            existing.getLibelle().equalsIgnoreCase(dto.getSpecialite().getLibelle()) &&
                                    existing.getNum().equalsIgnoreCase(dto.getSpecialite().getNum()),
                    MapperService::patchEntityFromDto
            );
            classe.setSpecialite(specialite);
        } else {
            classe.setSpecialite(null);
        }

        return classe;
    }

}
