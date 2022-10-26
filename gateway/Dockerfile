FROM openjdk:11
EXPOSE 8000
EXPOSE 9000
ARG JAR_FILE=target/gateway.jar
COPY ${JAR_FILE} /opt/gateway.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y
ENTRYPOINT ["java","-jar","/opt/gateway.jar"]