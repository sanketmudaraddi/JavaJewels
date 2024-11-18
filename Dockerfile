# Use an official Maven image to build the project
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and project files
COPY pom.xml /app/
COPY src /app/src/

# Package the application (this will create the JAR)
RUN mvn clean package

# Use a lightweight OpenJDK image to run the application
FROM openjdk:17-jdk-alpine

# Set the working directory in the new container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/jewellery-application-0.0.1-SNAPSHOT.jar /app/jewellery-application.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "/app/jewellery-application.jar"]
