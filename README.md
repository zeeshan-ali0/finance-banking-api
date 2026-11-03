# Finance Banking API

A personal finance and banking REST API built with Java 21, Spring Boot 4 and PostgreSQL.

## Run locally

Requires Java 21 and Docker.

```
docker compose up -d
./mvnw spring-boot:run
```

Health check: http://localhost:8080/actuator/health

The database runs on port 5433 to avoid clashing with a local PostgreSQL.# finance-banking-api