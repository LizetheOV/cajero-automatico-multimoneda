FROM adoptopenjdk/openjdk11:jdk-11.0.15_10-alpine-slim

WORKDIR /usr/src/app

COPY ./tasas .

RUN sed -i 's/\r$//' mvnw

RUN chmod +x mvnw

RUN ./mvnw clean install -DskipTests

RUN mv ./target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","application.jar"]
