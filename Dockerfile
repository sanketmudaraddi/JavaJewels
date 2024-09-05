# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY ./target/johari-application-0.0.1-SNAPSHOT.jar /app/johari-application.jar

# Expose the port that your Spring Boot app runs on
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/johari-application.jar"]
