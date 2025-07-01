package com.e221.pedagogieservice.runtime.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
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
        )
        // ❌ Retiré les @Server pour éviter les erreurs CORS
)
@Configuration
public class OpenApiConfig {
}
