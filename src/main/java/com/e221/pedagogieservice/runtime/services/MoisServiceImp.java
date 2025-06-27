package com.e221.pedagogieservice.runtime.services;

import com.e221.pedagogieservice.domain.dtos.requests.HoraireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.MoisDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.HoraireDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.MoisDtoResponse;
import com.e221.pedagogieservice.domain.models.Horaire;
import com.e221.pedagogieservice.domain.models.Mois;
import com.e221.pedagogieservice.domain.repositories.HoraireRepository;
import com.e221.pedagogieservice.domain.repositories.MoisRepository;
import com.e221.pedagogieservice.domain.services.HoraireService;
import com.e221.pedagogieservice.domain.services.MoisService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Transactional
@Slf4j
public class MoisServiceImp extends DefaultServiceImp<Mois, MoisDtoRequest, MoisDtoResponse> implements MoisService {
    private final MoisRepository moisRepository;

    public MoisServiceImp(MoisRepository moisRepository, DefaultSpecification<Mois> defaultSpecification) {
        super(moisRepository, defaultSpecification);
        this.moisRepository = moisRepository;
    }

}
