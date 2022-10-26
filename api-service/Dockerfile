FROM openjdk:11
EXPOSE 8300
EXPOSE 9300
EXPOSE 8301
EXPOSE 9301
ARG JAR_FILE=target/api-service.jar
COPY ${JAR_FILE} /opt/api-service.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y
ENTRYPOINT ["java","-jar","/opt/api-service.jar"]