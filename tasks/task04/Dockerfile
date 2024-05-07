FROM openjdk:17
LABEL maintaner = "Александр Богословский"
WORKDIR /app
COPY target/task04-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]