FROM openjdk:11
EXPOSE 8200
EXPOSE 9200
ARG JAR_FILE=target/config-server.jar
COPY ${JAR_FILE} /opt/config-server.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y
ENTRYPOINT ["java","-jar","/opt/config-server.jar"]