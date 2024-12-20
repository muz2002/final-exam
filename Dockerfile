# ---- Build Stage ----
FROM gradle:7.6.2-jdk17-alpine AS builder

# Set the working directory inside the container
WORKDIR /home/gradle/fina-exam

# Copy Gradle wrapper and settings first to leverage caching
COPY gradlew gradlew.bat settings.gradle build.gradle ./
COPY gradle gradle

# Ensure gradlew is executable
RUN chmod +x gradlew

# Copy the rest of the application source code
COPY src src

# Build the application (this will create the JAR in build/libs/)
RUN ./gradlew bootJar --no-daemon

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jdk-alpine

# Create a non-root user for security (optional but recommended)
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup

# Set the working directory for the runtime image
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=builder /home/gradle/fina-exam/build/libs/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Switch to the non-root user
USER appuser

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
