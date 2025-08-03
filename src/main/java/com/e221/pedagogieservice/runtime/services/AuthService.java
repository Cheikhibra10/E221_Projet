package com.e221.pedagogieservice.runtime.services;

import com.e221.pedagogieservice.domain.dtos.responses.LoginResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Value("${keycloak.auth-server-url}")
    private String keycloakBaseUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public LoginResponse login(String username, String password) {
        String tokenUrl = keycloakBaseUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        log.debug("Token URL: {}", tokenUrl);
        log.debug("Attempting login for user: {}", username);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=password" +
                "&client_id=" + clientId +
                "&username=" + username +
                "&password=" + password;

        log.debug("Request body: {}", body);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String.class);

            log.debug("Response status: {}", response.getStatusCode());
            log.debug("Response body: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode json = mapper.readTree(response.getBody());

                String accessToken = json.path("access_token").asText(null);
                String refreshToken = json.path("refresh_token").asText(null);
                long expiresIn = json.path("expires_in").asLong(0);

                if (accessToken == null) {
                    throw new RuntimeException("Access token manquant dans la réponse de Keycloak");
                }

                log.debug("Access token received: {}", accessToken);

                // Décoder le token JWT (partie payload)
                String[] parts = accessToken.split("\\.");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Format de JWT invalide : " + accessToken);
                }

                String payload = new String(Base64.getDecoder().decode(parts[1]), StandardCharsets.UTF_8);
                JsonNode payloadJson = mapper.readTree(payload);

                JsonNode roles = payloadJson.path("realm_access").path("roles");
                List<String> allowedRoles = List.of("ADMIN", "CLIENT");
                String role = "UNKNOWN";

                if (roles.isArray()) {
                    for (JsonNode r : roles) {
                        String rText = r.asText();
                        if (allowedRoles.contains(rText)) {
                            role = rText;
                            break;
                        }
                    }
                }

                log.debug("User role extracted from token: {}", role);

                return new LoginResponse(accessToken, refreshToken, expiresIn, role);
            } else {
                log.error("Authentication failed - HTTP Status: {}", response.getStatusCode());
                throw new RuntimeException("Échec d'authentification : " + response.getStatusCode());
            }

        } catch (HttpClientErrorException e) {
            log.error("HTTP Error during Keycloak login: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Erreur HTTP Keycloak : " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            log.error("Unexpected error during Keycloak login", e);
            throw new RuntimeException("Erreur inattendue lors de la connexion Keycloak", e);
        }
    }
}
