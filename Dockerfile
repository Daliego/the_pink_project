# Use an official OpenJDK image as the base image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the packaged Spring Boot JAR file into the container
COPY target/the-pink-project.jar ./app.jar

# Expose the port that your Spring Boot application uses
EXPOSE 8080

#Spring boot final build name and localization on target

#ADD target/the-pink-project.jar the-pink-project.jar

#Sprint entrypoint

#ENTRYPOINT ["java", "-jar", "/app.jar"]

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]