# Use official Java runtime as base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Gradle build output JAR
COPY build/libs/auth-service-*.jar auth-service.jar

# Expose app port
EXPOSE 8081

# Run app
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
