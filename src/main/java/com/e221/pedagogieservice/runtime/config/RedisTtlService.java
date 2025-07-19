package com.e221.pedagogieservice.runtime.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class RedisTtlService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTtlService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Récupère la durée de vie restante d'une clé (TTL) dans Redis.
     * @param cacheKey la clé complète (ex: "moisList" ou "module::42")
     * @return la durée restante avant expiration (ou null si non expirant)
     */
    public Duration getTtlAsDuration(String cacheKey) {
        Long seconds = redisTemplate.getExpire(cacheKey, TimeUnit.SECONDS);
        return (seconds == null || seconds < 0) ? null : java.time.Duration.ofSeconds(seconds);
    }

    /**
     * Retourne le TTL en secondes (ou -1 si permanent, -2 si la clé n’existe pas)
     */
    public Long getTtlInSeconds(String cacheKey) {
        return redisTemplate.getExpire(cacheKey, TimeUnit.SECONDS);
    }


}
