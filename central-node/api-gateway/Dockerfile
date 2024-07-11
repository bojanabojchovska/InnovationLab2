# Use official Java 11 JDK image as base
FROM openjdk:21-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory in the container
WORKDIR /api-gateway

# Copy the pom.xml and source code files
COPY pom.xml .
COPY src src

# Build the application without running tests
RUN mvn clean install

# Start the application
CMD ["java", "-jar", "target/api-gateway-1.0-SNAPSHOT.jar"]