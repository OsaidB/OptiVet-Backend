# First stage: Build the application
FROM maven:3.8-openjdk-21 AS build
LABEL authors="OAB2K"

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper files and the pom.xml
COPY pom.xml mvnw mvnw.cmd ./
COPY .mvn .mvn

# Download Maven dependencies (this step caches dependencies for faster builds)
RUN ./mvnw dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Build the Spring Boot application
RUN ./mvnw package -DskipTests

# Second stage: Run the application
FROM openjdk:21-jdk-slim
LABEL authors="OAB2K"

# Set the working directory
WORKDIR /app

# Expose the port your app will run on
EXPOSE 8080

# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
