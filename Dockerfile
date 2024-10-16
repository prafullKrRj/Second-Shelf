# Build stage
FROM gradle:7.5.1-jdk17 AS build
WORKDIR /app
COPY . /app
RUN chmod +x ./gradlew
RUN ./gradlew clean build --no-daemon --warning-mode all -x test

# Run stage
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /build/libs/secondshelf-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENV spring.datasource.url=${JDBC_DATABASE_URL}
ENV spring.datasource.username=${JDBC_DATABASE_USERNAME}
ENV spring.datasource.password=${JDBC_DATABASE_PASSWORD}
ENTRYPOINT ["java", "-jar", "/app/app.jar"]