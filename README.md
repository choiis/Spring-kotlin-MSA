# Spring-Cloud-MSA

## 준비

* Cassandra 서버를 준비한다
* table.sql 파일로 keyspace와 테이블을 만들어 둔다

* consul 설치 후 실행 한다

```bash
./consul agent -server -bootstrap -ui -client=0.0.0.0 -data-dir ./data --bind=127.0.0.1 &
```

* docker compose 설치 후 이 jaeger_all_in_one의 docker-compose.yml실행 
* docker로 Jaeger Tracing실행한다
```bash
docker-compose up
```

## 실행

### 전체 빌드
* ./mvnw clean install

### Gateway 실행
* ./mvnw -pl gateway spring-boot:run

### Service 실행
* ./mvnw -pl appkey-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev1"
* ./mvnw -pl appkey-service spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev2"
