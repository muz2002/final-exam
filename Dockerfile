# Stage 1: Build Image
FROM openjdk:19-jdk AS build
WORKDIR /app

# Copy Maven wrapper and pom.xml first for dependency resolution
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Ensure the Maven wrapper is executable
RUN chmod +x ./mvnw

# Download dependencies to leverage caching
RUN ./mvnw dependency:go-offline -B

# Now copy the source code
COPY src src

# Build the application, skip tests for faster build
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime Image
FROM openjdk:19-jdk
WORKDIR /app

# Copy the final jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]
