# Tourist Management API (Spring Boot)

A runnable Spring Boot 3 / Java 17 REST API with H2 database, seeded sample tourists,
Bearer-token validation, and Swagger UI.

## Requirements
- Java 17+
- Maven 3.6+

## Run
From this folder:

```bash
mvn spring-boot:run
```

Default development token: `dev-tourist-token-123`

For a different token, set an environment variable before starting:
- macOS/Linux: `export TOURIST_API_TOKEN='replace-with-a-long-random-secret'`
- Windows PowerShell: `$env:TOURIST_API_TOKEN='replace-with-a-long-random-secret'`

## Swagger testing
Open http://localhost:8080/swagger-ui.html

1. Click **Authorize**.
2. Enter `dev-tourist-token-123` (or your configured token). Swagger's HTTP Bearer scheme adds
   the `Authorization: Bearer <token>` header.
3. Try the tourist endpoints.

## API endpoints

All `/api/**` routes require this header:

```http
Authorization: Bearer dev-tourist-token-123
```

| Method | Path | Purpose |
|---|---|---|
| GET | `/api/tourists` | Retrieve the complete registered tourist list (dashboard bulk list) |
| GET | `/api/tourists/{id}` | Retrieve one tourist |
| PUT | `/api/tourists/{id}` | Update a tourist |
| PUT | `/api/tourists/{id}` with `X-Action: fetch` | Read-only PUT alias to retrieve one tourist, as requested |

**REST note:** GET is the conventional method for fetching data. The requested PUT fetch behavior
is provided as a compatibility alias requiring `X-Action: fetch`, while normal PUT updates the
record. This avoids ambiguity between reading and updating the same route.

### Example: fetch all
```bash
curl -H "Authorization: Bearer dev-tourist-token-123" http://localhost:8080/api/tourists
```

### Example: fetch one
```bash
curl -H "Authorization: Bearer dev-tourist-token-123" http://localhost:8080/api/tourists/1
```

### Example: requested PUT fetch alias
```bash
curl -X PUT -H "Authorization: Bearer dev-tourist-token-123" \
  -H "X-Action: fetch" http://localhost:8080/api/tourists/1
```

### Example: update a tourist
```bash
curl -X PUT http://localhost:8080/api/tourists/1 \
  -H "Authorization: Bearer dev-tourist-token-123" \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Aarav","lastName":"Sharma","email":"aarav@example.com","phone":"+91-9000000001","country":"India","city":"Hyderabad"}'
```

## Token behavior
- Missing, malformed, or incorrect Bearer token -> HTTP `401 Unauthorized`.
- Correct token -> request continues.
- Swagger UI and OpenAPI docs remain accessible; the actual `/api/**` operations are protected.
- Token comparison uses constant-time byte comparison.

## Database
H2 is in-memory and is initialized with three sample tourists at startup. Data resets when the app restarts.
H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:touristsdb`, user `sa`, blank password).

## Project structure
```text
src/main/java/com/example/tourists/
  config/       OpenAPI setup and sample data
  controller/   REST endpoints
  model/        JPA Tourist entity
  repository/   Spring Data repository
  security/     Bearer token filter
  service/      Tourist business logic
src/main/resources/application.properties
```
