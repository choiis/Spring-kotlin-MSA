FROM openjdk:11
EXPOSE 8100
EXPOSE 9100
EXPOSE 8101
EXPOSE 9101
ARG JAR_FILE=target/appkey-service.jar
COPY ${JAR_FILE} /opt/appkey-service.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y
ENTRYPOINT ["java","-jar","/opt/appkey-service.jar"]