FROM maven:3.9.2 AS builder
WORKDIR /opt/app
COPY . .
COPY ./target/EventLib-1.0-SNAPSHOT.jar /opt/app/
RUN mvn install:install-file -Dfile=/opt/app/EventLib-1.0-SNAPSHOT.jar -DgroupId=ru.melnikov.demo.gRPCtest.client -DartifactId=EventLib -Dversion=1.0-SNAPSHOT -Dpackaging=jar
RUN mvn install -Dmaven.test.skip




FROM openjdk:17-slim

WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar

ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]
