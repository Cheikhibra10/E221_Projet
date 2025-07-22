package com.e221.pedagogieservice.domain.utils;

import com.cheikh.commun.exceptions.EntityNotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;
import java.util.function.Consumer;

public final class DomainEntityHelper {

    private DomainEntityHelper() {}

    /* ------------------------------------------------------------------ */
    /*  Validation interne                                                */
    /* ------------------------------------------------------------------ */
    private static void requireValidId(Long id, String name) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(name + " id is required and must be > 0");
            // Remplace par BadRequestException si tu en utilises une :
            // throw new BadRequestException(name + " id is required and must be > 0");
        }
    }

    /* ------------------------------------------------------------------ */
    /*  Chargements stricts                                               */
    /* ------------------------------------------------------------------ */

    /**
     * Charge une entité par ID ou lève une EntityNotFoundException.
     */
    public static <T> T findStrictById(JpaRepository<T, Long> repository, Long id, Class<T> entityClass) {
        Objects.requireNonNull(repository, "repository");
        Objects.requireNonNull(entityClass, "entityClass");
        requireValidId(id, entityClass.getSimpleName());
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName() + " not found with id: " + id));
    }

    /**
     * Renvoie l'entité si elle existe, sinon null. Ne lève pas si absente.
     */
    public static <T> T findStrictByIdOrNull(JpaRepository<T, Long> repository, Long id, Class<T> entityClass) {
        Objects.requireNonNull(repository, "repository");
        Objects.requireNonNull(entityClass, "entityClass");
        if (id == null) return null;
        if (id <= 0) return null; // on ignore silencieusement les id invalides ici
        return repository.findById(id).orElse(null);
    }

    /**
     * Retourne une référence proxy (getReference) sans SELECT immédiat.
     * Lève IllegalArgumentException si id invalide. Peut lever EntityNotFoundException
     * plus tard lorsque l'entité est accédée (comportement dépendant du provider JPA).
     */
    public static <T> T getReferenceStrict(EntityManager em, Class<T> entityClass, Long id) {
        Objects.requireNonNull(em, "entityManager");
        Objects.requireNonNull(entityClass, "entityClass");
        requireValidId(id, entityClass.getSimpleName());
        try {
            return em.getReference(entityClass, id);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(entityClass.getSimpleName() + " not found with id: " + id);
        }
    }

    /* ------------------------------------------------------------------ */
    /*  Mise à jour                                                     */
    /* ------------------------------------------------------------------ */

    /**
     * Charge l'entité par ID, applique un Consumer de mise à jour, puis persiste.
     * Si fieldUpdater est null, renvoie simplement l'entité trouvée (sans save).
     */
    public static <T> T updateStrictById(
            JpaRepository<T, Long> repository,
            Long id,
            Class<T> entityClass,
            Consumer<T> fieldUpdater
    ) {
        T entity = findStrictById(repository, id, entityClass);
        if (fieldUpdater != null) {
            fieldUpdater.accept(entity);
            return repository.save(entity);
        }
        return entity;
    }

    /* ------------------------------------------------------------------ */
    /*  Attachement optionnel                                            */
    /* ------------------------------------------------------------------ */

    /**
     * Charge ou renvoie null si id null/<=0 ou non trouvé. Ne lève pas.
     * Pratique pour les relations optionnelles.
     */
    public static <T> T attachByIdOrNull(JpaRepository<T, Long> repository, Long id, Class<T> entityClass) {
        Objects.requireNonNull(repository, "repository");
        Objects.requireNonNull(entityClass, "entityClass");
        if (id == null || id <= 0) return null;
        return repository.findById(id).orElse(null);
    }
}
