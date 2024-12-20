# Stage 1: Build Image
FROM openjdk:19-jdk AS build
WORKDIR /app

# Copy Gradle wrapper and build files first for dependency caching
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Download dependencies (caches them for subsequent builds)
RUN ./gradlew --no-daemon build -x test || return 0

# Now copy the source code
COPY src src

# Build the application (skip tests for faster build if desired)
RUN ./gradlew --no-daemon clean build -x test

# Stage 2: Runtime Image
FROM openjdk:19-jdk
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]
