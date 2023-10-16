FROM ubuntu:latest AS build
RUN apt-get update

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM eclipse-temurin:20-jdk-alpine

EXPOSE 8080

COPY --from=build /target/todolist-0.0.1.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]