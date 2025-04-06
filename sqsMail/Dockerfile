FROM maven:3.8.4-eclipse-temurin-17 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app

RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-alpine


COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

RUN echo "docker build -t lucaseduardo76/email-produto-pedido:2.0 ." && \
    echo "docker login" && \
    echo "docker push lucaseduardo76/email-produto-pedido:2.0"

CMD ["java", "-jar", "app.jar"]
