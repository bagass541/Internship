FROM openjdk:17
LABEL maintaner = "Александр Богословский"
WORKDIR /app
COPY target/task05-1.0-SNAPSHOT.jar computer_club.jar
EXPOSE 8080
CMD ["java", "-jar", "computer_club.jar"]