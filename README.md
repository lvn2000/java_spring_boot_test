# Spring Boot Application

A simple Spring Boot web application with REST API endpoints.

## Project Structure

```
src/main/java/com/example/
├── Application.java              # Main Spring Boot application class
└── controller/
    └── HelloController.java      # REST API endpoints

src/main/resources/
└── application.properties        # Application configuration
```

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher

## Build & Run

### Build the project
```bash
mvn clean install
```

### Run the application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. Simple Hello Endpoint
**GET** `/api/hello`
```bash
curl http://localhost:8080/api/hello
```
Response:
```json
{
  "message": "Hello from Spring Boot!",
  "status": "success"
}
```

### 2. Personalized Greeting
**GET** `/api/hello/{name}`
```bash
curl http://localhost:8080/api/hello/John
```
Response:
```json
{
  "message": "Hello, John!",
  "status": "success"
}
```

### 3. Health Check
**GET** `/api/health`
```bash
curl http://localhost:8080/api/health
```
Response:
```json
{
  "status": "UP",
  "application": "Spring Boot App"
}
```

### 4. Echo Endpoint
**POST** `/api/echo`
```bash
curl -X POST http://localhost:8080/api/echo \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello"}'
```
Response:
```json
{
  "echo": {
    "message": "Hello"
  },
  "timestamp": 1644146000000
}
```

## Features

- Spring Boot 3.2.0
- Spring Web MVC
- Spring Data JPA with Hibernate
- H2 In-Memory Database
- Spring Boot Actuator for monitoring
- Lombok for reducing boilerplate
- Comprehensive test support

## Database

The application uses H2 in-memory database. You can access the H2 Console at:
```
http://localhost:8080/h2-console
```

Default credentials:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

## Actuator Endpoints

- Health: `http://localhost:8080/actuator/health`
- Info: `http://localhost:8080/actuator/info`
- Metrics: `http://localhost:8080/actuator/metrics`

## Development

### IDE Setup
If using IntelliJ IDEA or VS Code:
1. Install Java 17 SDK
2. Install Maven
3. Open the project folder as a Maven project
4. IDE should automatically recognize and configure the project

### Running Tests
```bash
mvn test
```

## License

This project is open source and available under the MIT License.
