# ======== STAGE 1: Build ========
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app
COPY . .

# Créer le dossier Maven
RUN mkdir -p /root/.m2

# Générer le fichier settings.xml avec le token GitHub
RUN echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>
<settings xmlns=\"http://maven.apache.org/SETTINGS/1.0.0\"
          xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"
          xsi:schemaLocation=\"http://maven.apache.org/SETTINGS/1.0.0
                              https://maven.apache.org/xsd/settings-1.0.0.xsd\">
  <servers>
    <server>
      <id>github</id>
      <username>Cheikhibra10</username>
      <password>\${GITHUB_TOKEN}</password>
    </server>
  </servers>
</settings>" > /root/.m2/settings.xml

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# ======== STAGE 2: Runtime ========
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copier uniquement le JAR compilé depuis le build
COPY --from=build /app/target/pedagogie-service.jar .

EXPOSE 8080

CMD ["java", "-jar", "pedagogie-service.jar"]
