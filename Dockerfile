# First stage: Build the application
FROM maven:3.9-eclipse-temurin-19 AS build
LABEL authors="OAB2K"

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw mvnw.cmd pom.xml ./
COPY .mvn .mvn

# Grant execution permission to the Maven wrapper (important for Unix environments)
RUN chmod +x mvnw

# Download Maven dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Second stage: Run the application
FROM eclipse-temurin:19-jdk
LABEL authors="OAB2K"

# Set the working directory
WORKDIR /app

# Expose the port your app will run on
EXPOSE 8080

# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
