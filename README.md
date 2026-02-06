# Spring Boot Country Info Service

A REST API service that retrieves country information using numeric IDs, transforms language codes to standard locales (e.g., `en` + `US` → `en-US`), and persists all transformations to a database with full transaction management.

## Features

- ✅ **Single REST Endpoint**: `/country-info?id={numeric_id}`
- ✅ **External API Integration**: Calls mock API with numeric country ID
- ✅ **Language Transformation**: 200+ language-country combinations
- ✅ **Database Persistence**: Stores all transformations with @Transactional
- ✅ **Error Handling**: Logs failed transformations to database
- ✅ **Complete Test Coverage**: 12 tests, 100% pass rate

## Project Structure

```
src/main/java/com/example/
├── Application.java                      # Spring Boot entry point
├── controller/
│   ├── HelloController.java              # REST endpoint handler (/country-info)
│   └── MockExternalApiController.java    # Mock external API (/api/external)
├── service/
│   ├── ExternalApiService.java           # API integration & transformation
│   └── LocaleStorageService.java         # Database persistence
├── model/
│   ├── ExternalApiResponse.java          # API response model
│   ├── LocaleData.java                   # JPA entity for database
│   └── LanguageLocale.java               # Enum with 200+ transformation rules
└── repository/
    └── LocaleDataRepository.java         # JPA repository

src/main/resources/
└── application.properties                # Application configuration

docs/
├── APPLICATION_OVERVIEW.md               # Quick reference guide
├── DOCUMENTATION.md                      # Complete component documentation
├── LOGIC_FLOW_PROOF.md                   # Process flow with proofs
└── NUMERIC_ID_IMPLEMENTATION.md          # Numeric ID parameter details
```

## Prerequisites

- **Java 21** or higher
- **Maven 3.8** or higher

## Build & Run

### Build the project
```bash
mvn clean package
```

### Run the application
```bash
java -jar target/spring-boot-app-1.0.0.jar
```

The application will start on `http://localhost:9090`

## API Endpoint

### Get Country Info with Language Transformation

**GET** `/country-info?id={numeric_id}`

#### Request
```bash
curl http://localhost:9090/country-info?id=1
```

#### Response
```json
{
  "lang": "en-US",
  "country": "US",
  "id": "1",
  "name": "Country: 1",
  "description": "Mock response from external API for country ID: 1"
}
```

#### Country ID Mapping

| ID | Country | Language | Result |
|----|---------|----------|--------|
| 1 | US | en | **en-US** |
| 2 | SE | sv | **sv-SE** |
| 3 | LV | lv | **lv-LV** |
| 4 | BR | pt | **pt-BR** |
| 5 | GB | en | **en-GB** |

#### Example Requests
```bash
# US English
curl http://localhost:9090/country-info?id=1

# Swedish
curl http://localhost:9090/country-info?id=2

# Latvian
curl http://localhost:9090/country-info?id=3

# Portuguese (Brazil)
curl http://localhost:9090/country-info?id=4

# British English
curl http://localhost:9090/country-info?id=5
```

## Technology Stack

| Component | Version |
|-----------|--------|
| **Spring Boot** | 3.2.0 |
| **Java** | 21 |
| **Database** | H2 (In-Memory) |
| **ORM** | Hibernate/JPA |
| **Server Port** | 9090 |
| **Testing** | JUnit 5 + Mockito |

## Application Flow

```
1. Receive numeric ID (1-5)
   ↓
2. Call external API: /api/external?id={id}
   ↓
3. Get response: {lang: "en", country: "US", ...}
   ↓
4. Transform: "en" + "US" → "en-US"
   ↓
5. Save to database (with @Transactional)
   ↓
6. Return transformed response
```

## Database

The application uses H2 in-memory database.

**Schema**:
- Table: `locale_data`
- Columns: `id` (PK), `original_lang`, `original_country`, `transformed_lang`, `success`, `error_message`, `created_at`
- Rows: Auto-created from transformations

## Running Tests

```bash
mvn clean test
```

**Test Results**: 12 tests, 100% pass rate
- 9 service layer tests (transformation logic)
- 2 controller integration tests (endpoint functionality)
- 1 application startup test

## Documentation

For detailed information, see:
- **[APPLICATION_OVERVIEW.md](APPLICATION_OVERVIEW.md)** - Quick reference with flow diagrams
- **[DOCUMENTATION.md](DOCUMENTATION.md)** - Complete component documentation (17 sections)
- **[LOGIC_FLOW_PROOF.md](LOGIC_FLOW_PROOF.md)** - Process flow with working proofs
- **[NUMERIC_ID_IMPLEMENTATION.md](NUMERIC_ID_IMPLEMENTATION.md)** - Numeric ID parameter details

## Development

### IDE Setup
If using IntelliJ IDEA or VS Code:
1. Install Java 21 SDK
2. Install Maven 3.8+
3. Open the project folder as a Maven project
4. IDE will automatically recognize and configure the project

## License

Open source project.
