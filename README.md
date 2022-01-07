# Spring-Cloud-MSA

## 준비

* Cassandra 서버를 준비한다
* table.sql 파일로 keyspace와 테이블을 만들어 둔다

* Consul 1.11.1버전 기준
* https://www.consul.io/downloads 에서 1.11.1 버전 운영체제 맞게 다운
* consul 압축 해제 후 Server로 실행 한다

```bash
./consul agent -server -bootstrap -ui -client=0.0.0.0 -data-dir ./data --bind=127.0.0.1 &
```
* http://localhost:8500 에서 확인


* docker compose 설치 후 이 jaeger_all_in_one또는 jaeger_msa의 docker-compose.yml실행 
* docker로 Jaeger Tracing실행한다
```bash
docker-compose up
```
* http://localhost:16686 에서 확인

## 실행

### 전체 빌드
* ./mvnw clean install

### Gateway 실행
* ./mvnw -pl gateway spring-boot:run

### Service 실행
* ./mvnw -pl appkey-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev1"
* ./mvnw -pl appkey-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev2"

* ./mvnw -pl api-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev1"
* ./mvnw -pl api-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev2"

## docker-compose로 모두 실행

### 전체 빌드
* mvn clean pacakge spring-boot:repackage

### docker-compose
* docker-compose up