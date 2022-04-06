FROM maven:3.8.5-openjdk-11-slim AS build

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build /build/target/adobetask-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "adobetask-0.0.1-SNAPSHOT.jar"]
