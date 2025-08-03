# Étape 1 : build
FROM eclipse-temurin:21-jdk AS builder

# Recevoir le token en build
ARG GITHUB_TOKEN

# Le rendre disponible dans les instructions suivantes
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

# Créer le dossier .m2 avant de copier settings.xml
RUN mkdir -p /root/.m2

WORKDIR /app

# Copier les fichiers Maven
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Copier le fichier settings.xml AVANT le build
COPY maven/settings.xml /root/.m2/settings.xml

# Copier le code source
COPY src/ ./src/

# Compiler avec les bonnes configs
RUN chmod +x mvnw && ./mvnw clean package -DskipTests --settings /root/.m2/settings.xml

# Étape 2 : image finale
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
