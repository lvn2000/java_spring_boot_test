# Spring Boot Country Info Service - Quick Overview

## 🎯 Application Purpose
A REST API service that retrieves country information from an external API using numeric country IDs, transforms language codes to standard locales, and persists the results to a database.

---

## 📊 Application Flow

```
CLIENT REQUEST
    │
    ▼
GET /country-info?id=1
    │
    ▼
┌──────────────────────────────────────┐
│   HelloController                    │
│   (REST Endpoint Handler)            │
│                                      │
│   @GetMapping("/country-info")       │
│   Parameter: id=1 (Long)             │
└────────────┬─────────────────────────┘
             │
             ▼
┌──────────────────────────────────────────────────────────┐
│   ExternalApiService                                     │
│   (Business Logic)                                       │
│                                                          │
│   1. Build URL: http://localhost:9090/api/external?id=1      │
│   2. Call RestTemplate.getForObject()                         │
│   3. Receive: {lang:"en", country:"US", ...}                │
└────────────┬─────────────────────────────────────────────┘
             │
             ▼
┌──────────────────────────────────────┐
│   LanguageLocale Enum                │
│   (Transformation Rules)             │
│                                      │
│   mapLocale("en", "US")              │
│        ↓                             │
│   LanguageLocale.EN_US               │
│        ↓                             │
│   "en-US"  ← TRANSFORMED!            │
└────────────┬─────────────────────────┘
             │
             ▼
┌──────────────────────────────────────┐
│   LocaleStorageService               │
│   (Database Persistence)             │
│                                      │
│   saveSuccessfulTransformation()     │
│   Creates LocaleData entity          │
│   ↓                                  │
│   original_lang: "en"                │
│   original_country: "US"             │
│   transformed_lang: "en-US" ✅       │
│   success: true                      │
└────────────┬─────────────────────────┘
             │
             ▼
┌──────────────────────────────────────┐
│   H2 Database (In-Memory)            │
│                                      │
│   INSERT INTO locale_data (...)      │
│   Database Commit ✅                 │
└────────────┬─────────────────────────┘
             │
             ▼
┌──────────────────────────────────────┐
│   Response to Client                 │
│                                      │
│   HTTP 200 OK                        │
│   {                                  │
│     "lang": "en-US",   ← TRANSFORMED │
│     "country": "US",                 │
│     "id": "US"                       │
│   }                                  │
└──────────────────────────────────────┘
```

---

## 🔄 Key Components

| Component | Purpose |
|-----------|---------|
| **HelloController** | REST endpoint handler - receives `/country-info?id={id}` requests |
| **ExternalApiService** | Calls external API and manages transformation logic |
| **LanguageLocale** | Enum with 200+ language-country transformation rules |
| **LocaleStorageService** | Persists transformations to database with @Transactional |
| **LocaleDataRepository** | JPA repository for database queries |
| **LocaleData** | JPA entity representing stored transformation record |
| **H2 Database** | In-memory relational database storing all transformations |

---

## 📝 Example Usage

### Request
```bash
curl http://localhost:9090/country-info?id=1
```

### Processing
```
1. Controller receives: id=1
2. Service calls: /api/external?id=1
3. Gets response: {lang:"en", country:"US"} (ID 1 = US)
4. Transforms: "en" + "US" → "en-US"
5. Saves to DB: LocaleData(originalLang:"en", transformedLang:"en-US", success:true)
6. Returns transformed response
```

### Response
```json
{
  "id": "1",
  "lang": "en-US",
  "country": "US",
  "name": "Country: 1"
}
```

### Country ID Mapping
| ID | Country | Lang → Transformed |
|----|---------|-------------------|
| 1 | US | en → en-US |
| 2 | SE | sv → sv-SE |
| 3 | LV | lv → lv-LV |
| 4 | BR | pt → pt-BR |
| 5 | GB | en → en-GB |

---

## 🗄️ Database Schema

**Table: locale_data**

| Column | Type | Description |
|--------|------|-------------|
| `id` | Long (PK) | Auto-generated ID |
| `original_lang` | String | Language from external API (e.g., "en") |
| `original_country` | String | Country from external API (e.g., "US") |
| `transformed_lang` | String | Transformed result (e.g., "en-US") |
| `success` | Boolean | true=success, false=error |
| `error_message` | String | Error details if failed |
| `created_at` | DateTime | Timestamp of record |

---

## ✅ Test Coverage

- ✅ 9 service layer tests (transformation logic)
- ✅ 2 controller integration tests (endpoint functionality)  
- ✅ 1 application startup test
- **Total: 12/12 tests passing**

---

## 🚀 Deployment

```bash
# Build
mvn clean package

# Run
java -jar target/spring-boot-app-1.0.0.jar

# Access with numeric ID
http://localhost:9090/country-info?id=1     # US (en → en-US)
http://localhost:9090/country-info?id=2     # SE (sv → sv-SE)
http://localhost:9090/country-info?id=3     # LV (lv → lv-LV)
http://localhost:9090/country-info?id=4     # BR (pt → pt-BR)
```

---

## 🔐 Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 21
- **Database**: H2 (in-memory)
- **ORM**: Hibernate/JPA
- **HTTP Client**: RestTemplate
- **Server**: Apache Tomcat (embedded)
- **Port**: 9090

---

## 📊 Transaction Flow

```
START TRANSACTION
    ↓
CALL EXTERNAL API (RestTemplate)
    ↓
TRANSFORM LANGUAGE CODE (LanguageLocale enum)
    ↓
INSERT INTO DATABASE (repository.save())
    ↓
COMMIT TRANSACTION
    ↓
RETURN 200 OK WITH TRANSFORMED DATA

[ON ERROR]
CATCH EXCEPTION
    ↓
INSERT ERROR RECORD TO DATABASE
    ↓
THROW EXCEPTION
    ↓
RETURN 500 ERROR
```

---

**In Summary**: A lightweight REST service that calls an external API, transforms language codes using enum-based rules, and persists all transformations (success & failure) to an H2 in-memory database with full transaction management.
