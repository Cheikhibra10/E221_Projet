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

# Create settings.xml with GitHub token expanded
RUN mkdir -p /root/.m2 && \
    echo "<settings xmlns=\"http://maven.apache.org/SETTINGS/1.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd\"><servers><server><id>github</id><username>Cheikhibra10</username><password>${GITHUB_TOKEN}</password></server></servers></settings>" \
    > /root/.m2/settings.xml

# Make wrapper executable and build
RUN chmod +x mvnw && ./mvnw clean package -DskipTests --settings /root/.m2/settings.xml

# -------- Run Stage --------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy jar from builder
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
