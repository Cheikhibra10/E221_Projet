package com.e221.pedagogieservice.runtime.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                title = "OpenApi specification - Cheikh",
                version = "1.0",
                description = "Documentation OpenAPI pour le projet Spring Boot de pédagogie.",
                contact = @Contact(
                        name = "Cheikh",
                        email = "contact@cheikhcoding.com",
                        url = "https://cheikhcoding.com/course"
                ),
                license = @License(
                        name = "Licence libre",
                        url = "https://some-url.com"
                ),
                termsOfService = "https://cheikhcoding.com/terms"
        ),
        security = @SecurityRequirement(name = "bearerAuth") // <-- dis à Swagger d'exiger un token sur toutes les routes
)
@SecurityScheme(
        name = "bearerAuth", // <-- ce nom est utilisé dans @SecurityRequirement
        type = SecuritySchemeType.HTTP,
        scheme = "bearer", // <-- active le champ "Authorize" avec Bearer Token
        bearerFormat = "JWT" // <-- indique que le token attendu est un JWT
)
@Configuration
public class OpenApiConfig {
}


