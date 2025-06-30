# Étape 1 : Build de l’application avec Maven
FROM eclipse-temurin:21-jdk AS builder

# Argument pour GitHub Token
ARG GITHUB_TOKEN
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

# Dossier de travail
WORKDIR /app

# Copier le code source
COPY . .

# Configurer Maven pour GitHub Packages
RUN mkdir -p /root/.m2
COPY maven/settings.xml /root/.m2/settings.xml
RUN sed -i "s|\${env.GITHUB_TOKEN}|${GITHUB_TOKEN}|" /root/.m2/settings.xml

# Rendre le wrapper exécutable
RUN chmod +x ./mvnw

# Build du projet sans tests
RUN ./mvnw clean install -DskipTests \
    -Dmaven.wagon.http.retryHandler.count=5 \
    -Dhttp.connection.timeout=60000 \
    -Dhttp.read.timeout=60000

# Étape 2 : Image finale minimale
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=builder /app/target/pedagogie-service.jar app.jar

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
