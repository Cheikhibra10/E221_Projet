# Utiliser l’image OpenJDK
FROM eclipse-temurin:21-jdk

# Définir le répertoire de travail
WORKDIR /app

# Copier tout le code source dans le conteneur
COPY . .

# Créer le dossier .m2 et générer le fichier settings.xml avec les identifiants GitHub
RUN mkdir -p /root/.m2 && \
    echo '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"' > /root/.m2/settings.xml && \
    echo '         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"' >> /root/.m2/settings.xml && \
    echo '         xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">' >> /root/.m2/settings.xml && \
    echo '  <servers>' >> /root/.m2/settings.xml && \
    echo '    <server>' >> /root/.m2/settings.xml && \
    echo '      <id>github</id>' >> /root/.m2/settings.xml && \
    echo '      <username>Cheikhibra10</username>' >> /root/.m2/settings.xml && \
    echo '      <password>${GITHUB_TOKEN}</password>' >> /root/.m2/settings.xml && \
    echo '    </server>' >> /root/.m2/settings.xml && \
    echo '  </servers>' >> /root/.m2/settings.xml && \
    echo '</settings>' >> /root/.m2/settings.xml

# Rendre le wrapper Maven exécutable
RUN chmod +x mvnw

# Construire l'application sans les tests
RUN ./mvnw clean install -DskipTests

# Exposer le port de l’application
EXPOSE 8080

# Lancer l’application
CMD ["java", "-jar", "target/pedagogie-service.jar"]
