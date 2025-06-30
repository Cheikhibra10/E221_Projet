FROM eclipse-temurin:21-jdk AS builder

ARG GITHUB_TOKEN
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

WORKDIR /app

# Copier uniquement le code de pedagogie-service
COPY . .

# Settings Maven pour GitHub
RUN mkdir -p /root/.m2
COPY maven/settings.xml /root/.m2/settings.xml
RUN sed -i "s|\${env.GITHUB_TOKEN}|${GITHUB_TOKEN}|" /root/.m2/settings.xml

RUN chmod +x ./mvnw

# Build avec accès à GitHub Packages
RUN ./mvnw clean install -DskipTests

# Image finale
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/pedagogie-service.jar app.jar

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
