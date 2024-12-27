# Use a Maven image to build the project
FROM maven:3.8.6-openjdk-11 AS build

# Set the working directory
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use a smaller image to run the application
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/flowable-demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8084

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]