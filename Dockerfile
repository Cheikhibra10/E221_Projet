FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

# Crée le dossier Maven config
RUN mkdir -p /root/.m2

# Génère le settings.xml avec le token GitHub depuis la variable d'environnement
RUN echo '<settings>
  <servers>
    <server>
      <id>github</id>
      <username>Cheikhibra10</username>
      <password>'"${GITHUB_TOKEN}"'</password>
    </server>
  </servers>
</settings>' > /root/.m2/settings.xml

RUN chmod +x mvnw

RUN ./mvnw clean install -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/pedagogie-service.jar"]
