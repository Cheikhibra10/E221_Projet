# -------- Build Stage --------
FROM eclipse-temurin:21-jdk AS builder

# Build-time GitHub token for private Maven dependencies
ARG GITHUB_TOKEN

WORKDIR /app

# Copy Maven wrapper and project files
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src/ ./src/

# Create settings.xml and build the project in one step
RUN mkdir -p /root/.m2 && \
    printf '<settings>\n  <servers>\n    <server>\n      <id>github</id>\n      <username>Cheikhibra10</username>\n      <password>%s</password>\n    </server>\n  </servers>\n</settings>\n' "$GITHUB_TOKEN" > /root/.m2/settings.xml && \
    chmod +x mvnw && \
    ./mvnw clean package -DskipTests --settings /root/.m2/settings.xml

# -------- Run Stage --------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
