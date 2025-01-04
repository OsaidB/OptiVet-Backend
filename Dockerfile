# First stage: Build the application
FROM ubuntu:latest AS build
LABEL authors="OAB2K"

# Install necessary dependencies and Java
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    curl \
    unzip \
    && apt-get clean

# Set the working directory
WORKDIR /app

# Copy the application source code into the container
COPY . .

# Grant execution permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Build the Spring Boot application
RUN ./gradlew bootJar --no-daemon

# Second stage: Run the application
FROM openjdk:21-jdk-slim
LABEL authors="OAB2K"

# Set the working directory
WORKDIR /app

# Expose the port your app will run on
EXPOSE 8080

# Copy the built jar file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
