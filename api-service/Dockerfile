FROM openjdk:11
EXPOSE 8300
EXPOSE 9300
EXPOSE 8301
EXPOSE 9301
ARG JAR_FILE=target/api-service-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/api-service-1.0-SNAPSHOT.jar
WORKDIR /opt
ENTRYPOINT ["java","-jar","api-service-1.0-SNAPSHOT.jar"]