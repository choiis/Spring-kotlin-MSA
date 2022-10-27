FROM openjdk:11
EXPOSE 8200 9200
ARG JAR_FILE=target/config-server.jar
COPY ${JAR_FILE} /opt/config-server.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y && chmod 755 /opt/config-server.jar
ENTRYPOINT ["java","-jar","/opt/config-server.jar"]