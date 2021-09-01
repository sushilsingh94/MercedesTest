
FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} file-storage-server.jar
ENTRYPOINT ["java","-jar","/file-storage-server.jar"]