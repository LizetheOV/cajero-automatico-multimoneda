FROM adoptopenjdk/openjdk11:jdk-11.0.15_10-alpine-slim

COPY target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","application.jar"]
