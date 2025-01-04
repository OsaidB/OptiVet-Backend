# First stage: Build the application
FROM maven:3.9-openjdk-19 AS build
LABEL authors="OAB2K"

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper files and the pom.xml
COPY pom.xml mvnw mvnw.cmd ./
COPY .mvn .mvn

# Download Maven dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the Spring Boot application
RUN ./mvnw package -DskipTests

# Second stage: Run the application
FROM openjdk:19-jdk-slim
LABEL authors="OAB2K"

# Set the working directory
WORKDIR /app

# Expose the port your app will run on
EXPOSE 8080

# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
