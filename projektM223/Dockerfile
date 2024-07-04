# Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Copy the application's jar file into the container
COPY target/projektM223a.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
