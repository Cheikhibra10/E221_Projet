package com.e221.pedagogieservice.runtime.services.base;

import com.cheikh.commun.core.GenericEntity;
import com.cheikh.commun.core.GenericRepository;
import com.cheikh.commun.exceptions.BadRequestException;
import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.exceptions.InternalServerErrorException;
import com.cheikh.commun.services.MapperService;
import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.constants.ErrorsMessages;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.utils.LoggingUtil;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
@Transactional
@Slf4j
public abstract class DefaultServiceImp<T extends GenericEntity<T>, D, R> implements DefaultService<T, D, R> {
    //    private final RepositoryFactory repositoryFactory;
    private final GenericRepository<T> repository;
    private final DefaultSpecification<T> defaultSpecification;
    private final Class<T> tClass;
    private final Class<R> rClass;

    @SuppressWarnings("unchecked")
    public DefaultServiceImp(GenericRepository<T> repository, DefaultSpecification<T> defaultSpecification) {
        this.repository = repository;
        this.defaultSpecification = defaultSpecification;
        var type = (ParameterizedType) getClass().getGenericSuperclass();
        this.tClass = (Class<T>) type.getActualTypeArguments()[0];
        this.rClass = (Class<R>) type.getActualTypeArguments()[2];
    }

    private GenericRepository<T> getRepository() {
        return this.repository;
    }

    @Override
    public R create(D d) {
        try {
            LoggingUtil.logInfo(this.getClass(), "create",
                    String.format("begin add new < %s >", tClass.getName()));
            T entity = MapperService.mapToEntity(d, tClass);
            entity = createRelationships(entity, d);
            T savedEntity = getRepository().save(entity);
            LoggingUtil.logInfo(
                    this.getClass(), "create", String.format("< %s > added successfully", tClass.getName()));
            return MapperService.mapToDtoResponse(entity, rClass);
        } catch (DataIntegrityViolationException ex) {
            LoggingUtil.logError(this.getClass(), "create",
                    String.format("%s %s", tClass.getName(), ErrorsMessages.ENTITY_EXISTS));
            throw new BadRequestException(String.format("%s %s", tClass.getName(), ErrorsMessages.ENTITY_EXISTS));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            LoggingUtil.logError(this.getClass(), "create",
                    String.format("%s %s", ErrorsMessages.ADD_ENTITY_ERROR, tClass.getName()));
            throw new InternalServerErrorException(String.format("%s %s", ErrorsMessages.ADD_ENTITY_ERROR, tClass.getName()));
        }
    }

    @Override
    public R update(long id, D d) {
        try {
            T entity = getRepository()
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ErrorsMessages.ENTITY_NOT_FOUND_MESSAGE));

            LoggingUtil.logInfo(this.getClass(), "update",
                    String.format("begin update < %s > with id: %s", tClass.getName(), id));
            MapperService.patchEntityFromDto(entity, d);
            entity = updateRelationships(entity, d);
            T savedEntity = getRepository().save(entity);
            LoggingUtil.logInfo(this.getClass(), "update",
                    String.format("< %s > with id: %s updated successfully", tClass.getName(), id));
            return MapperService.mapToDtoResponse(savedEntity, rClass);

        } catch (DataIntegrityViolationException ex) {
            LoggingUtil.logError(this.getClass(), "update",
                    String.format("%s %s", tClass.getName(), ErrorsMessages.ENTITY_EXISTS));
            throw new BadRequestException(String.format("%s %s", tClass.getName(), ErrorsMessages.ENTITY_EXISTS));
        } catch (Exception ex) {
            LoggingUtil.logError(this.getClass(), "update",
                    String.format("%s %s", ErrorsMessages.ADD_ENTITY_ERROR, tClass.getName()));
            throw new InternalServerErrorException(String.format("%s %s", ErrorsMessages.ADD_ENTITY_ERROR, tClass.getName()));
        }
    }


    public List<R> findAllA() {
        List<T> list = repository.findAll(defaultSpecification.isArchiveFalse());
        log.info("Found {} elements", list.size());
        return MapperService.mapToListDto(list, rClass);
    }

    @Override
    public List<R> findAll() {
        List<T> list = getRepository().findAll();
        return MapperService.mapToListDto(list, rClass);
    }

    @Override
    public R delete(Long id) {
        T entity = getEntityById(id);
        repository.delete(entity);
        return MapperService.mapToDtoResponse(entity, rClass);
    }

    @Override
    public R archive(Long id) {
        return null;
    }

    @Override
    public R getById(Long id) {
        return MapperService.mapToDtoResponse(getEntityById(id), rClass);
    }

    @Override
    public T getEntityById(Long id) {
        return getRepository()
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorsMessages.ENTITY_NOT_FOUND_MESSAGE));
    }

    protected T createRelationships(T entity, D dto) {
        return entity;
    }

    protected T updateRelationships(T entity, D dto) {
        return entity;
    }
}