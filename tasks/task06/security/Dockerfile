FROM openjdk:17
LABEL maintainer = "Александр Богословский"
WORKDIR /app
COPY target/security-0.0.1-SNAPSHOT.jar security.jar
EXPOSE 8090
CMD ["java", "-jar", "security.jar"]