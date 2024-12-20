# Stage 1: Build Image
FROM openjdk:19-jdk AS build
WORKDIR /app

# Install necessary packages
RUN apt-get update && apt-get install -y findutils && rm -rf /var/lib/apt/lists/*

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
RUN chmod +x ./gradlew
RUN ./gradlew --no-daemon clean build -x test

COPY src src
RUN ./gradlew --no-daemon clean build -x test

# Stage 2: Runtime Image
FROM openjdk:19-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/app/app.jar"]
