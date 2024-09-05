# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from your local machine to the container
COPY target/houseofJohari-0.0.1-SNAPSHOT.jar /app/houseofJohari.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/houseofJohari.jar"]
