
# Use Maven with Java 21 for building
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set working directory in the container
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application (skip tests to speed up)
RUN mvn clean package -DskipTests

# Use a lightweight Java 21 JDK runtime image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the runtime container
WORKDIR /app

# Copy the JAR file from the build stage and rename it to FsEcommerce.jar
COPY --from=builder /app/target/FsEcommerce-0.0.1-SNAPSHOT.jar FsEcommerce.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 2000

# Start the Spring Boot application using the project-named JAR
ENTRYPOINT ["java", "-jar", "FsEcommerce.jar"]
