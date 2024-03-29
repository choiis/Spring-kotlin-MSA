# Spring-Cloud-MSA

* Spring MSA api gateway & service discovery with consul & Jaeger & MySQL JPA & Cassandra

## Prepare project environment

* Prepare the Cassandra server
* Create a keyspace and table with table.sql file
* As of version 1.11.1 of Consul
* Download version 1.11.1 according to your operating system from https://www.consul.io/downloads
* After unzip Consul, run it as Server

```bash
./consul agent -server -bootstrap -ui -client=0.0.0.0 -data-dir ./data --bind=127.0.0.1 &
```
* Check at http://localhost:8500


* After installing docker compose, run docker-compose.yml in this jaeger_all_in_one or jaeger_msa directory
* Execute Jaeger Tracing with docker
```bash
docker-compose up
```
* Check at http://localhost:16686

* Execute docker-compose.yml in mysql folder to run mysql server and create table
```bash
docker-compose up
```

## Execution

### Build all
* mvn clean install

### Gateway Execution
* mvn -pl gateway spring-boot:run

### Config Server Execution
* mvn -pl config-server spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev1"

### Service Execution
* mvn -pl appkey-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev1"
* mvn -pl appkey-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev2"

* mvn -pl api-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev1"
* mvn -pl api-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev2"

### api service swagger
* You can use api service document at http://localhost:8100/swagger-ui/index.html

## Run all with docker-compose

### Build all
* mvn -pl gateway clean package spring-boot:repackage docker:build
* mvn -pl config-server clean package spring-boot:repackage docker:build
* mvn -pl appkey-service clean package spring-boot:repackage docker:build
* mvn -pl api-service clean package spring-boot:repackage docker:build

### docker-compose
* docker-compose up

### check Jaeger trace
* You can search traces between MSA services at http://localhost:16686
![Jaeger1](http://imageresizer-dev-serverlessdeploymentbucket-xapz1q6q9exe.s3-website-ap-northeast-1.amazonaws.com/gitpng/jaeger_search.png)

* You can see the structure of calls between MSA services
![Jaeger2](http://imageresizer-dev-serverlessdeploymentbucket-xapz1q6q9exe.s3-website-ap-northeast-1.amazonaws.com/gitpng/jaeger_topology.png)


### check logs
* You can view the docker container logs under the logs directory by connecting to the docker volume.