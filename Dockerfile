FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . .

# Injecter le token dans settings.xml avec ARG et ENV
ARG GITHUB_TOKEN
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

RUN mkdir -p /root/.m2 && \
    echo "<settings xmlns=\"http://maven.apache.org/SETTINGS/1.0.0\"" > /root/.m2/settings.xml && \
    echo "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" >> /root/.m2/settings.xml && \
    echo "         xsi:schemaLocation=\"http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd\">" >> /root/.m2/settings.xml && \
    echo "  <servers>" >> /root/.m2/settings.xml && \
    echo "    <server>" >> /root/.m2/settings.xml && \
    echo "      <id>github</id>" >> /root/.m2/settings.xml && \
    echo "      <username>Cheikhibra10</username>" >> /root/.m2/settings.xml && \
    echo "      <password>${GITHUB_TOKEN}</password>" >> /root/.m2/settings.xml && \
    echo "    </server>" >> /root/.m2/settings.xml && \
    echo "  </servers>" >> /root/.m2/settings.xml && \
    echo "</settings>" >> /root/.m2/settings.xml

RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "target/pedagogie-service.jar"]
