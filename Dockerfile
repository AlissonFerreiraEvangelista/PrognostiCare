FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c "touch /app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-jar", "/app.jar"]

