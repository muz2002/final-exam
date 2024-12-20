FROM openjdk:19-jdk AS build
WORKDIR /app

# Copy build files and gradle wrapper
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
RUN chmod +x ./gradlew

# Download dependencies if needed (optional)
# RUN ./gradlew --no-daemon build -x test || true

# Now copy source code
COPY src src

# Build the application
RUN ./gradlew --no-daemon clean build -x test

FROM openjdk:19-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
