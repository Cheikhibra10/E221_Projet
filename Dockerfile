FROM eclipse-temurin:21-jdk AS builder

ARG GITHUB_TOKEN
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

WORKDIR /app

# Copier uniquement le code du service
COPY . .

# Ajouter les settings Maven GitHub
RUN mkdir -p /root/.m2
COPY maven/settings.xml /root/.m2/settings.xml
RUN sed -i "s|\${env.GITHUB_TOKEN}|${GITHUB_TOKEN}|" /root/.m2/settings.xml

RUN chmod +x ./mvnw

# Build sans tests
RUN ./mvnw clean install -DskipTests \
    -Dmaven.wagon.http.retryHandler.count=5 \
    -Dhttp.connection.timeout=60000 \
    -Dhttp.read.timeout=60000

# Image finale
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=builder /app/target/pedagogie-service.jar app.jar

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
