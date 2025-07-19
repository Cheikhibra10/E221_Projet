package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.ClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.DocumentDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ClasseDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.DocumentDtoResponse;
import com.e221.pedagogieservice.domain.models.Classe;
import com.e221.pedagogieservice.domain.models.Document;

public interface DocumentService extends DefaultService<Document, DocumentDtoRequest, DocumentDtoResponse> {

}

