package com.e221.pedagogieservice.runtime.services;

import com.e221.pedagogieservice.domain.dtos.requests.MentionDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.PaysDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.MentionDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.PaysDtoResponse;
import com.e221.pedagogieservice.domain.models.Mention;
import com.e221.pedagogieservice.domain.models.Pays;
import com.e221.pedagogieservice.domain.repositories.MentionRepository;
import com.e221.pedagogieservice.domain.repositories.PaysRepository;
import com.e221.pedagogieservice.domain.services.MentionService;
import com.e221.pedagogieservice.domain.services.PaysService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class PaysServiceImp extends DefaultServiceImp<Pays, PaysDtoRequest, PaysDtoResponse> implements PaysService {
    private final PaysRepository paysRepository;

    public PaysServiceImp(PaysRepository paysRepository, DefaultSpecification<Pays> defaultSpecification) {
        super(paysRepository, defaultSpecification);
        this.paysRepository = paysRepository;
    }
}