# First stage: Build the application
FROM ubuntu:latest AS build
LABEL authors="OAB2K"

# Install dependencies and Java
RUN apt-get update && apt-get install openjdk-21-jdk -y

# Copy the application source code into the container
COPY . .

# Build the Spring Boot application
RUN ./gradlew bootJar --no-daemon

# Second stage: Run the application
FROM openjdk:21-jdk-slim
LABEL authors="OAB2K"

# Expose the port your app will run on
EXPOSE 8080

# Copy the built jar file from the build stage
COPY --from=build /build/libs/demo-1.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
