# Use the official OpenJDK image from the Docker Hub
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your local machine to the container
COPY target/houseofJohari-0.0.1-SNAPSHOT.jar /app/houseofJohari.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8082

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "houseofJohari.jar"]
