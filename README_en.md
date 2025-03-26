# Clean Architecture Backend Java

This project demonstrates a clean architecture implementation in a Java Spring Boot application, following hexagonal architecture patterns and best practices.

## Project Structure

```
clean-architecture-backend-java
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── adapters/
│   │   │   │   ├── in/
│   │   │   │   │   └── web/             # Web controllers (entry points)
│   │   │   │   └── out/
│   │   │   │       └── persistance/     # Database repositories (exit points)
│   │   │   ├── application/             # Use cases
│   │   │   ├── commons/
│   │   │   │   ├── dto/                 # Data transfer objects
│   │   │   │   └── mapper/              # Object mappers
│   │   │   ├── config/                  # Configuration classes
│   │   │   └── domain/                  # Domain model
│   │   └── resources/
│   │       ├── application.yml          # Main application configuration
│   │       └── application-local.yml    # Local profile configuration
│   └── test/
│       ├── java/com/example/demo/
│       │   ├── integration/             # Integration tests with Cucumber
│       │   └── unit/                    # Unit tests
│       └── resources/
│           ├── features/                # Cucumber feature files
│           └── application-test.yml     # Test profile configuration
└── pom.xml                              # Maven configuration
```

## Technologies

- Java 21
- Spring Boot 3.4.4
- Spring Data JPA
- PostgreSQL
- MapStruct
- Cucumber for BDD testing
- JUnit 5
- Mockito
- SpringDoc OpenAPI for API documentation

## Architecture

This project implements Clean Architecture with a hexagonal approach:

1. **Domain Layer**: Core business logic and entities  
2. **Application Layer**: Use cases implementing business rules  
3. **Adapters Layer**:  
   - Input adapters: Controllers receiving HTTP requests  
   - Output adapters: Repositories for database operations  

## Getting Started

### Prerequisites

- Java 21  
- Maven 3.8+  
- PostgreSQL database  

### Database Setup

Create two PostgreSQL databases:  
- `demo_dev_db` for local development  
- `demo_test_db` for testing  

With default credentials:  
- Username: `postgres`  
- Password: `1234`  

Or modify the configuration in [`src/main/resources/application-local.yml`](src/main/resources/application-local.yml) and [`src/test/resources/application-test.yml`](src/test/resources/application-test.yml).

### Running the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on a random port in the local profile.

## Testing

The project includes both unit tests and integration tests:

```bash
# Run all tests
mvn test

# Run only unit tests
mvn test -Dtest=*UnitTest

# Run only Cucumber tests
mvn test -Dtest=*CucumberTest
```

## API Endpoints

- `POST /users?name={name}`: Register a new user

## 🐳 Using Docker

You can use Docker to start a PostgreSQL container with the two required databases for local development and testing (`demo_dev_db` and `demo_test_db`).

### Required Files

1. **`docker-compose.yml`**  
   Defines a PostgreSQL container:

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15
    container_name: clean-arch-postgres
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 1234
      POSTGRES_DB: demo_dev_db
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
      - ./init:/docker-entrypoint-initdb.d

volumes:
  pgdata:
```

2. **`init/create-test-db.sql`**  
   SQL script to automatically create the test database when the container starts:

```sql
CREATE DATABASE demo_test_db;
```

### How to Run

1. Create a directory called `init/` in the root of the project if it doesn't exist.
2. Inside that directory, create the file `create-test-db.sql` with the content shown above.
3. In the project root, run:

```bash
docker-compose up -d
```

This will start a PostgreSQL container accessible at `localhost:5432` with the following databases:

- `demo_dev_db`: for local development
- `demo_test_db`: for tests

Default credentials:
- **Username**: `postgres`
- **Password**: `1234`

You can change these values in the `docker-compose.yml` and update them in your `application-local.yml` and `application-test.yml` configuration files.

## License

This project is private and for demonstration purposes only.
