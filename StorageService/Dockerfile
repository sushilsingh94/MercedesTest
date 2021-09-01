FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} storage-service.jar
ENTRYPOINT ["java","-jar","/storage-service.jar"]