FROM openjdk:17
LABEL maintainer = "Александр Богословский"
WORKDIR /app
COPY target/catalog-0.0.1-SNAPSHOT.jar catalog.jar
EXPOSE 8091
CMD ["java", "-jar", "catalog.jar"]