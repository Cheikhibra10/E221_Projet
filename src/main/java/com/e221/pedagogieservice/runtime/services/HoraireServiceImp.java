package com.e221.pedagogieservice.runtime.services;

import com.e221.pedagogieservice.domain.dtos.requests.HoraireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.HoraireDtoResponse;
import com.e221.pedagogieservice.domain.models.Horaire;
import com.e221.pedagogieservice.domain.repositories.HoraireRepository;
import com.e221.pedagogieservice.domain.services.HoraireService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class HoraireServiceImp extends DefaultServiceImp<Horaire, HoraireDtoRequest, HoraireDtoResponse> implements HoraireService {
    private final HoraireRepository horaireRepository;

    public HoraireServiceImp(HoraireRepository horaireRepository, DefaultSpecification<Horaire> defaultSpecification) {
        super(horaireRepository, defaultSpecification);
        this.horaireRepository = horaireRepository;
    }

}
