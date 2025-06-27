# Start from OpenJDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build the app
RUN ./mvnw clean install -DskipTests

# Expose the port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/pedagogie-service.jar"]
