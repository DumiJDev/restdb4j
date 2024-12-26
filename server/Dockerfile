FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY . .

RUN mvn clean package

FROM eclipse-temurin:21_35-jre-alpine

WORKDIR /app

ENV RESTDB_NAME="RestDB4J"
ENV RESTDB_DB_PLATFORM="h2"
ENV RESTDB_DB_PORT=""
ENV RESTDB_DB_HOST="localhost"
ENV RESTDB_DB_USER="sa"
ENV RESTDB_DB_PWD="password"
ENV RESTDB_DB_NAME="restdb.db"
ENV RESTDB_DRIVER_CLASS=""
ENV RESTDB_DB_URL=""
ENV RESTDB_SERVER_NAME="restdb"

EXPOSE 8080

COPY --from=builder /app/target/restdb.jar restdb4j.jar
ENTRYPOINT ["java", "-jar", "restdb4j.jar"]