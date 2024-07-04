FROM openjdk:21
ADD projektM223/target/spring-reservation-docker.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]