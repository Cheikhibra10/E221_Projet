# Étape 1 : Build avec Maven Wrapper et JDK 21
FROM eclipse-temurin:21-jdk AS builder

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers Maven nécessaires pour profiter du cache
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Télécharger les dépendances (cache utile si aucun changement dans les deps)
RUN ./mvnw dependency:go-offline

# Copier tout le projet
COPY . .

# Compiler et packager l'application (skip tests pour build rapide)
RUN ./mvnw clean package -DskipTests

# Étape 2 : Image finale pour exécution
FROM eclipse-temurin:21-jre

# Répertoire de l'application dans le conteneur
WORKDIR /app

# Copier le JAR depuis l'étape précédente
COPY --from=builder /app/target/*.jar app.jar

# Exposer le port utilisé par Spring Boot
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
