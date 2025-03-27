# Clean Architecture Backend Java (English Version)

This project demonstrates a clean architecture implementation in a Java Spring Boot application, following hexagonal architecture patterns and best practices.

## Project Structure

```
clean-architecture-backend-java
├── init/                                 # Initialization files
│   └── create-test-db.sql                # SQL script to create the test database
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── adapters/                 # Adapters that handle system input/output
│   │   │   │   ├── input/                # Input adapters (e.g., web controllers)
│   │   │   │   │   └── web/              # HTTP controllers (REST APIs)
│   │   │   │   │       └── dto/          # Data Transfer Objects used in the web layer
│   │   │   │   └── output/               # Output adapters (persistence, external services)
│   │   │   │       └── persistance/      # Data persistence implementation
│   │   │   │           └── entity/       # JPA entities representing database tables
│   │   │   │           └── jpa/          # JPA repositories for database access
│   │   │   ├── application/              # Application logic: use cases and orchestration
│   │   │   │   └── ports/                # Interfaces (ports) defining communication between layers
│   │   │   │       ├── input/            # Input ports representing use cases (called from outside)
│   │   │   │       └── output/           # Output ports implemented by adapters
│   │   │   │   └── service/              # Implementations of use cases
│   │   │   ├── domain/                   # Pure business logic (domain model)
│   │   │   └── infrastructure/           # System configurations and integrations
│   │   │       └── config/               # Spring configuration (Beans, Security, etc.)
│   │   │       └── mapper/               # Mappers (MapStruct or manual) between DTOs and entities
│   │   └── resources/                    # Project configuration files and static resources
│   │       ├── application.yml           # Main environment configuration
│   │       └── application-local.yml     # Configuration for local development environment
│   └── test/
│       ├── java/com/example/demo/
│       │   ├── integration/              # Integration tests
│       │   │   └── config/               # Configurations for integration testing
│       │   │   └── steps/                # Step definitions for Cucumber BDD tests
│       │   └── unit/                     # Unit tests for individual components
│       └── resources/
│           ├── features/                 # Cucumber .feature files for BDD scenarios
│           └── application-test.yml      # Configuration for the test environment
├── docker-compose.yml                    # Docker setup for services (e.g., database)
└── pom.xml                               # Maven project configuration file
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
4. **Infrastructure Layer**: System configurations and integrations

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

## License

This project is private and for demonstration purposes only.


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


# Backend Java con Arquitectura Limpia (Versión en Español)

Este proyecto demuestra una implementación de arquitectura limpia en una aplicación Java con Spring Boot, siguiendo los patrones de arquitectura hexagonal y buenas prácticas.

## Estructura del Proyecto

```
clean-architecture-backend-java
├── init/                                 # Archivos de inicialización
│   └── create-test-db.sql                # Script SQL para crear la base de datos de pruebas
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── adapters/                 # Adaptadores que permiten la entrada/salida del sistema
│   │   │   │   ├── input/                # Adaptadores de entrada (por ejemplo, controladores web)
│   │   │   │   │   └── web/              # Controladores HTTP (REST)
│   │   │   │   │       └── dto/          # Objetos de transferencia de datos usados en la capa web
│   │   │   │   └── output/               # Adaptadores de salida (persistencia, servicios externos)
│   │   │   │       └── persistance/      # Implementación de la persistencia de datos
│   │   │   │           └── entity/       # Entidades JPA que representan tablas de la base de datos
│   │   │   │           └── jpa/          # Repositorios JPA para acceso a la base de datos
│   │   │   ├── application/              # Lógica de aplicación: casos de uso y orquestación
│   │   │   │   └── ports/                # Interfaces (puertos) que definen la comunicación entre capas
│   │   │   │       ├── input/            # Interfaces que representan casos de uso (desde el dominio)
│   │   │   │       └── output/           # Interfaces de salida que deben implementar los adaptadores
│   │   │   │   └── service/              # Implementaciones de los casos de uso
│   │   │   ├── domain/                   # Lógica de negocio pura (modelo de dominio)
│   │   │   └── infrastructure/           # Configuraciones e integraciones del sistema
│   │   │       └── config/               # Configuración de Spring (Beans, Security, etc.)
│   │   │       └── mapper/               # Mappers (MapStruct o manuales) entre DTOs y entidades
│   │   └── resources/                    # Archivos de configuración y recursos del proyecto
│   │       ├── application.yml           # Configuración principal del entorno
│   │       └── application-local.yml     # Configuración específica para entorno local
│   └── test/
│       ├── java/com/example/demo/
│       │   ├── integration/              # Pruebas de integración
│       │   │   └── config/               # Configuración para pruebas de integración
│       │   │   └── steps/                # Definición de pasos para tests con Cucumber
│       │   └── unit/                     # Pruebas unitarias de componentes individuales
│       └── resources/
│           ├── features/                 # Archivos .feature de Cucumber para pruebas BDD
│           └── application-test.yml      # Configuración específica para entorno de test
├── docker-compose.yml                    # Configuración de Docker para levantar servicios (BD, etc.)
└── pom.xml                               # Archivo de configuración de Maven
```

## Tecnologías

- Java 21
- Spring Boot 3.4.4
- Spring Data JPA
- PostgreSQL
- MapStruct
- Cucumber para pruebas BDD
- JUnit 5
- Mockito
- SpringDoc OpenAPI para documentación de APIs

## Arquitectura

Este proyecto implementa la Arquitectura Limpia con un enfoque hexagonal:

1. **Capa de Dominio**: Lógica de negocio principal y entidades  
2. **Capa de Aplicación**: Casos de uso que implementan las reglas de negocio  
3. **Capa de Adaptadores**:  
   - Adaptadores de entrada: Controladores que reciben peticiones HTTP  
   - Adaptadores de salida: Repositorios para operaciones con la base de datos
4. **Capa de Infraestructura**: Configuraciones e integraciones del sistema

## Primeros Pasos

### Requisitos Previos

- Java 21  
- Maven 3.8+  
- Base de datos PostgreSQL  

### Configuración de la Base de Datos

Crea dos bases de datos en PostgreSQL:  
- `demo_dev_db` para desarrollo local  
- `demo_test_db` para pruebas  

Con las credenciales por defecto:  
- Usuario: `postgres`  
- Contraseña: `1234`  

O modifica la configuración en los archivos [`src/main/resources/application-local.yml`](src/main/resources/application-local.yml) y [`src/test/resources/application-test.yml`](src/test/resources/application-test.yml).

### Ejecutar la Aplicación

```bash
# Construir el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación se iniciará en un puerto aleatorio utilizando el perfil local.

## Pruebas

El proyecto incluye tanto pruebas unitarias como de integración:

```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar solo las pruebas unitarias
mvn test -Dtest=*UnitTest

# Ejecutar solo las pruebas de Cucumber
mvn test -Dtest=*CucumberTest
```

## Endpoints de la API

- `POST /users?name={name}`: Registra un nuevo usuario

## Licencia

Este proyecto es privado y solo para fines demostrativos.


## 🐳 Uso con Docker

Puedes usar Docker para levantar una base de datos PostgreSQL con las dos bases de datos necesarias para el entorno local y de pruebas (`demo_dev_db` y `demo_test_db`).

### Archivos necesarios

1. **`docker-compose.yml`**  
   Define un contenedor de PostgreSQL:

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
   Script para crear la base de datos de testing automáticamente al levantar el contenedor:

```sql
CREATE DATABASE demo_test_db;
```

### Pasos para ejecutarlo

1. Crea un directorio `init/` en la raíz del proyecto si no existe.
2. Dentro de ese directorio, crea el archivo `create-test-db.sql` con el contenido indicado arriba.
3. En la raíz del proyecto, ejecuta:

```bash
docker-compose up -d
```

Esto levantará un contenedor PostgreSQL accesible en `localhost:5432` con las siguientes bases de datos:

- `demo_dev_db`: para desarrollo local
- `demo_test_db`: para tests

Credenciales por defecto:
- **Usuario**: `postgres`
- **Contraseña**: `1234`

Puedes cambiar estos valores en `docker-compose.yml` y actualizarlos en tus archivos `application-local.yml` y `application-test.yml`.
