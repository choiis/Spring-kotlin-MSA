FROM openjdk:11
EXPOSE 8300 9300 8301 9301
ARG JAR_FILE=target/api-service.jar
COPY ${JAR_FILE} /opt/api-service.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y && chmod 755 /opt/api-service.jar
ENTRYPOINT ["java","-jar","/opt/api-service.jar"]