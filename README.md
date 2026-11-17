# SOA Lab - Microservices Architecture

## Services

| Service | Port | Description |
|---------|------|-------------|
| **eureka-service** | `9000` | Netflix Eureka Discovery Server |
| **gateway-service** | `8000` | Spring Cloud API Gateway |
| **driver-service** | `8001` | Driver management (Login & Register) |

---

## Project Structure

```
soalab/
├── eureka-service/        # Service Discovery (port 9000)
├── gateway-service/       # API Gateway (port 8000)
├── driver-service/        # Driver Microservice (port 8001)
└── README.md
```

---

## How to Run

> ⚠️ **Start services in this order:**

### 1. Start Eureka Server (first!)
```bash
cd eureka-service
mvn spring-boot:run
```
Open: http://localhost:9000

### 2. Start Driver Service
```bash
cd driver-service
mvn spring-boot:run
```

### 3. Start Gateway
```bash
cd gateway-service
mvn spring-boot:run
```

---

## Driver Service API Endpoints

### Register a Driver
**POST** `http://localhost:8001/api/drivers/register`
```json
{
  "email": "driver@example.com",
  "password": "password123",
  "name": "John Doe",
  "phone": "9876543210",
  "licenseNumber": "DL-1234567890"
}
```

### Login
**POST** `http://localhost:8001/api/drivers/login`
```json
{
  "email": "driver@example.com",
  "password": "password123"
}
```

### Health Check
**GET** `http://localhost:8001/api/drivers/health`

---

## Via Gateway (port 8000)

All driver endpoints are also accessible through the gateway:

| Direct | Via Gateway |
|--------|-------------|
| `POST http://localhost:8001/api/drivers/register` | `POST http://localhost:8000/driver/api/drivers/register` |
| `POST http://localhost:8001/api/drivers/login` | `POST http://localhost:8000/driver/api/drivers/login` |

---

## H2 Console (Driver Service)

Access the in-memory database UI at:
http://localhost:8001/h2-console

- **JDBC URL:** `jdbc:h2:mem:driverdb`
- **Username:** `sa`
- **Password:** *(empty)*

---

## Tech Stack

- Java 17
- Spring Boot 3.2.0
- Spring Cloud 2023.0.0
- Netflix Eureka (Service Discovery)
- Spring Cloud Gateway (API Gateway)
- Spring Data JPA + H2 (Driver Service)
