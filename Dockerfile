# Étape 1 : Builder le module commun
FROM eclipse-temurin:21-jdk AS commun-builder

WORKDIR /app/commun

# Copie du settings.xml pour accès à GitHub Packages
COPY ../maven/settings.xml /root/.m2/settings.xml

COPY ../commun .
RUN chmod +x ./mvnw && ./mvnw clean install -DskipTests

# Étape 2 : Builder pedagogie-service
FROM eclipse-temurin:21-jdk AS pedagogie-builder

WORKDIR /app/pedagogie-service

COPY ../maven/settings.xml /root/.m2/settings.xml

COPY pedagogie-service/ .
COPY --from=commun-builder /root/.m2 /root/.m2

RUN chmod +x ./mvnw && ./mvnw clean install -DskipTests

# Étape finale : image d'exécution
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=pedagogie-builder /app/pedagogie-service/target/pedagogie-service.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
