package com.e221.pedagogieservice.runtime.services;

import com.e221.pedagogieservice.domain.dtos.requests.ParcoursDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ParcoursDtoResponse;
import com.e221.pedagogieservice.domain.models.Parcours;
import com.e221.pedagogieservice.domain.repositories.ParcoursRepository;
import com.e221.pedagogieservice.domain.services.ParcoursService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Transactional
@Slf4j
public class ParcoursServiceImp extends DefaultServiceImp<Parcours, ParcoursDtoRequest, ParcoursDtoResponse> implements ParcoursService {
    private final ParcoursRepository parcoursRepository;

    public ParcoursServiceImp(ParcoursRepository parcoursRepository, DefaultSpecification<Parcours> defaultSpecification) {
        super(parcoursRepository, defaultSpecification);
        this.parcoursRepository = parcoursRepository;
    }


}
