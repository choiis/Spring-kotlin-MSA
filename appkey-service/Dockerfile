FROM openjdk:11
EXPOSE 8100 9100 8101 9101
ARG JAR_FILE=target/appkey-service.jar
COPY ${JAR_FILE} /opt/appkey-service.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y && chmod 755 /opt/appkey-service.jar
ENTRYPOINT ["java","-jar","/opt/appkey-service.jar"]