FROM openjdk:19-jdk AS build
WORKDIR /app

# Install xargs (and potentially other needed tools)
RUN apt-get update && apt-get install -y findutils && rm -rf /var/lib/apt/lists/*

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew
COPY src src

# Run Gradle build
RUN ./gradlew --no-daemon clean build -x test

FROM openjdk:19-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
