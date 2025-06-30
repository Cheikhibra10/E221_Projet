# Étape 1 : build
FROM eclipse-temurin:21-jdk AS builder

ARG GITHUB_TOKEN
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

WORKDIR /app

# Copier fichiers Maven
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src/ ./src/
COPY maven/settings.xml /root/.m2/settings.xml

# Télécharger les dépendances et compiler
RUN chmod +x mvnw && ./mvnw clean package -DskipTests --settings /root/.m2/settings.xml

# Étape 2 : image d'exécution
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
