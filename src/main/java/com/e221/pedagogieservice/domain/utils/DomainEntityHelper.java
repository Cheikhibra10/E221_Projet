package com.e221.pedagogieservice.domain.utils;

import com.cheikh.commun.core.GenericEntity;
import com.cheikh.commun.core.GenericRepository;
import com.cheikh.commun.exceptions.BadRequestException;
import com.cheikh.commun.exceptions.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DomainEntityHelper {
    public static <T, D> T findOrCreateStrict(
            JpaRepository<T, Long> repository,
            D dto,
            Class<T> entityClass,
            Function<Root<T>, Predicate> businessPredicate, //  Accepts JPA predicate
            BiConsumer<T, D> fieldUpdater,
            EntityManager entityManager //  Required for Criteria API
    ) {
        Objects.requireNonNull(repository);
        Objects.requireNonNull(dto);

        try {
            //  Check if ID is present
            Field idField = dto.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(dto);

            if (idValue instanceof Long id && id > 0) {
                return repository.findById(id)
                        .map(existing -> {
                            if (fieldUpdater != null) fieldUpdater.accept(existing, dto);
                            return repository.save(existing);
                        })
                        .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
            }
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}

        //  Build JPA Criteria Query for uniqueness check
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<T> root = query.from(entityClass);
        query.select(cb.count(root)).where(businessPredicate.apply(root));

        Long count = entityManager.createQuery(query).getSingleResult();
        if (count > 0) {
            throw new BadRequestException("Entity %s with same business key already exists"
                    .formatted(entityClass.getSimpleName()));
        }

        //  Create new entity
        try {
            T newEntity = entityClass.getDeclaredConstructor().newInstance();
            if (fieldUpdater != null) fieldUpdater.accept(newEntity, dto);
            return repository.save(newEntity);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot create new instance of " + entityClass.getSimpleName(), e);
        }
    }

    public static <T extends GenericEntity<T>, D> List<T> findOrCreateListStrict(
            GenericRepository<T> repository,
            List<D> dtoList,
            Class<T> entityClass,
            Function<D, Specification<T>> specificationProvider,
            BiConsumer<T, D> fieldUpdater,
            EntityManager entityManager
    ) {
        Objects.requireNonNull(dtoList);
        return dtoList.stream()
                .map(dto -> {
                    try {
                        Field idField = dto.getClass().getDeclaredField("id");
                        idField.setAccessible(true);
                        Object idValue = idField.get(dto);

                        if (idValue instanceof Long id && id > 0) {
                            return repository.findById(id)
                                    .map(existing -> {
                                        if (fieldUpdater != null) fieldUpdater.accept(existing, dto);
                                        return repository.save(existing);
                                    })
                                    .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
                        }
                    } catch (Exception ignored) {}

                    // Check uniqueness by Specification
                    List<T> existing = repository.findAll(specificationProvider.apply(dto));
                    if (!existing.isEmpty()) {
                        throw new BadRequestException("Entity with same business key already exists");
                    }

                    // Create new
                    try {
                        T newEntity = entityClass.getDeclaredConstructor().newInstance();
                        if (fieldUpdater != null) fieldUpdater.accept(newEntity, dto);
                        return repository.save(newEntity);
                    } catch (Exception e) {
                        throw new IllegalStateException("Cannot create new instance of " + entityClass.getSimpleName(), e);
                    }
                })
                .toList();
    }



    public static <T, D> T findOrUpdate(
            JpaRepository<T, Long> repository,
            D dto,
            Class<T> entityClass,
            Function<T, Boolean> matchCriteria,
            BiConsumer<T, D> fieldUpdater
    ) {
        Objects.requireNonNull(repository, "Repository cannot be null");
        Objects.requireNonNull(dto, "DTO cannot be null");
        Objects.requireNonNull(entityClass, "Entity class cannot be null");
        Objects.requireNonNull(matchCriteria, "Match criteria cannot be null");

        BeanWrapper dtoWrapper = new BeanWrapperImpl(dto);

        // ✅ 1. Check if DTO has ID
        Object idValue = dtoWrapper.getPropertyValue("id");
        if (idValue instanceof Long id && id > 0) {
            return repository.findById(id)
                    .map(existing -> {
                        if (fieldUpdater != null) fieldUpdater.accept(existing, dto);
                        return repository.save(existing);
                    })
                    .orElseThrow(() -> new EntityNotFoundException(
                            entityClass.getSimpleName() + " not found with ID: " + id
                    ));
        }

        // ✅ 2. Fallback: search by business key if no ID
        Optional<T> optional = repository.findAll().stream()
                .filter(e -> {
                    try {
                        return matchCriteria.apply(e);
                    } catch (Exception ex) {
                        return false;
                    }
                })
                .findFirst();

        if (optional.isPresent()) {
            T entity = optional.get();
            if (fieldUpdater != null) fieldUpdater.accept(entity, dto);
            return repository.save(entity);
        }

        // ✅ 3. Prevent creating empty entity
        if (isDtoEmpty(dto)) {
            throw new IllegalArgumentException("DTO has no ID and no valid data to create a new entity");
        }

        // ✅ 4. Create new entity
        try {
            T newEntity = entityClass.getDeclaredConstructor().newInstance();
            if (fieldUpdater != null) fieldUpdater.accept(newEntity, dto);
            return repository.save(newEntity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create new entity: " + entityClass.getSimpleName(), e);
        }
    }

    // ✅ Utility: Check if DTO fields are all null (except ID)
    private static boolean isDtoEmpty(Object dto) {
        BeanWrapper wrapper = new BeanWrapperImpl(dto);
        for (PropertyDescriptor pd : wrapper.getPropertyDescriptors()) {
            String name = pd.getName();
            if (!"class".equals(name) && !"id".equals(name)) {
                Object value = wrapper.getPropertyValue(name);
                if (value != null) return false;
            }
        }
        return true;
    }



}
