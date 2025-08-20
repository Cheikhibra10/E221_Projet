# -------- Build Stage --------
FROM eclipse-temurin:21-jdk AS builder

# Pass GitHub token for private dependencies
ARG GITHUB_TOKEN
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

WORKDIR /app

# Copy Maven wrapper and project files
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src/ ./src/
COPY maven/settings.xml ./settings.xml

# Create the settings.xml from the env variable
# Créer settings.xml depuis la variable Railway
RUN mkdir -p /root/.m2 && echo "$RAILWAY_SETTINGS_XML" > /root/.m2/settings.xml
# Make wrapper executable and build
RUN chmod +x mvnw && ./mvnw clean package -DskipTests --settings ./settings.xml

# -------- Run Stage --------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy jar from builder
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
