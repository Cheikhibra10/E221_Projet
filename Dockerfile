# Étape 1 : builder commun
FROM eclipse-temurin:21-jdk AS commun-builder
WORKDIR /app/commun
COPY maven/settings.xml /root/.m2/settings.xml
COPY commun/ .
RUN chmod +x ./mvnw && ./mvnw clean install -DskipTests

# Étape 2 : builder pedagogie-service
FROM eclipse-temurin:21-jdk AS pedagogie-builder
WORKDIR /app/pedagogie-service
COPY maven/settings.xml /root/.m2/settings.xml
COPY pedagogie-service/ .
COPY --from=commun-builder /root/.m2 /root/.m2
RUN chmod +x ./mvnw && ./mvnw clean install -DskipTests

# Étape finale
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=pedagogie-builder /app/pedagogie-service/target/pedagogie-service.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
