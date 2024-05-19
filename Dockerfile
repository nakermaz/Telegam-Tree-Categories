FROM openjdk:17
ADD /docker/*.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]