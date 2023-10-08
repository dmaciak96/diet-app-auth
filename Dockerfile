#Build stage
FROM maven:3.6.3-openjdk-17-slim AS builder
WORKDIR /webdiet-auth
COPY src ./src
COPY pom.xml ./
RUN mvn -f ./pom.xml clean package

#Package stage
FROM openjdk:22-jdk-slim AS runner
WORKDIR /webdiet-auth
COPY --from=builder /webdiet-auth/target/authorization-service-*.jar ./webdiet-auth.jar
COPY --from=builder /webdiet-auth/src/main/resources/application.yml ./application.yml
CMD ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5000", "./webdiet-auth.jar"]