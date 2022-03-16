FROM openjdk:11
EXPOSE 8100
EXPOSE 9100
EXPOSE 8101
EXPOSE 9101
ARG JAR_FILE=target/appkey-service-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/appkey-service-1.0-SNAPSHOT.jar
WORKDIR /opt
ENTRYPOINT ["java","-jar","appkey-service-1.0-SNAPSHOT.jar"]