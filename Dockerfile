FROM amazoncorretto:17-alpine
WORKDIR /app
COPY target/roomba-0.0.1-SNAPSHOT.jar app/roomba.jar
ENTRYPOINT ["java", "-Xms128m", "-Xmx512m", "-jar", "app/roomba.jar"]