FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY . .

RUN mkdir -p /root/.m2

# Injecter un settings.xml contenant l'authentification GitHub
RUN echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\
<settings xmlns=\"http://maven.apache.org/SETTINGS/1.0.0\">\n\
  <servers>\n\
    <server>\n\
      <id>github</id>\n\
      <username>Cheikhibra10</username>\n\
      <password>${GITHUB_TOKEN}</password>\n\
    </server>\n\
  </servers>\n\
</settings>" > /root/.m2/settings.xml

RUN chmod +x mvnw

RUN ./mvnw clean install -DskipTests --settings /root/.m2/settings.xml

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/pedagogie-service.jar .

EXPOSE 8080

CMD ["java", "-jar", "pedagogie-service.jar"]
