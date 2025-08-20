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

# Create Maven settings.xml using printf to handle special characters safely
RUN mkdir -p /root/.m2 && \
    printf '<settings>\n  <servers>\n    <server>\n      <id>github</id>\n      <username>Cheikhibra10</username>\n      <password>%s</password>\n    </server>\n  </servers>\n</settings>\n' "$GITHUB_TOKEN" > /root/.m2/settings.xml

# Make Maven wrapper executable and build the project
RUN chmod +x mvnw && ./mvnw clean package -DskipTests --settings /root/.m2/settings.xml

# -------- Run Stage --------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
