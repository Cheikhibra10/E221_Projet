package com.e221.pedagogieservice.runtime.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.cache.ttl")
public class CacheTtlProperties {

    private Map<String, Long> ttl = new HashMap<>(); // ← Ajoute une valeur par défaut
    @PostConstruct
    public void debugConfig() {
        System.out.println("============ DEBUG TTL CONFIG ============");
        System.out.println(" → Contenu brut TTL : " + ttl);
    }

}
