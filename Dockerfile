# ===========================
# Stage 1: Build the Application
# ===========================
FROM gradle:8.2.1-jdk17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle build files first to leverage Docker layer caching
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Download dependencies without building the project
RUN gradle build --no-daemon -x test

# Copy the rest of the application source code
COPY src ./src

# Package the application into an executable JAR
RUN gradle bootJar --no-daemon -x test

# ===========================
# Stage 2: Run the Application
# ===========================
FROM openjdk:17.0.1-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar demo.jar

# Expose the application port as defined in your configuration
EXPOSE 8000

# Define the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "demo.jar"]
