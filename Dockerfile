FROM eclipse-temurin:21-jdk AS builder

# Inject token comme ARG pour le passer à Maven
ARG GITHUB_TOKEN
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

WORKDIR /app

# Copier les fichiers Maven
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# 🟡 Copier le settings.xml personnalisé dans /root/.m2/
COPY maven/settings.xml /root/.m2/settings.xml

# Téléchargement des dépendances
RUN ./mvnw dependency:go-offline

# Puis on copie tout le reste du projet
COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
