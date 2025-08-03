# Étape 1 : build
FROM eclipse-temurin:21-jdk AS builder

# Recevoir le token en build
ARG GITHUB_TOKEN
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

# Créer le dossier Maven local
RUN mkdir -p /root/.m2

WORKDIR /app

# Copier les fichiers Maven
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Copier le fichier settings.xml
COPY maven/settings.xml /root/.m2/settings.xml

# Remplacer la variable dans settings.xml par la vraie valeur du token
RUN sed -i "s|\${env.GITHUB_TOKEN}|${GITHUB_TOKEN}|" /root/.m2/settings.xml

# Copier le code source
COPY src/ ./src/

# Compiler avec le bon fichier settings.xml
RUN chmod +x mvnw && ./mvnw clean package -DskipTests --settings /root/.m2/settings.xml

# Étape 2 : image d'exécution
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
