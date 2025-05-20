# Use official OpenJDK image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file to the image
COPY target/aiwriter-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Set environment variable for port (Render expects this)
ENV PORT=8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
