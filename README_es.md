# Backend Java con Arquitectura Limpia

Este proyecto demuestra una implementación de arquitectura limpia en una aplicación Java con Spring Boot, siguiendo los patrones de arquitectura hexagonal y buenas prácticas.

## Estructura del Proyecto

```
clean-architecture-backend-java
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── adapters/
│   │   │   │   ├── in/
│   │   │   │   │   └── web/             # Controladores web (puntos de entrada)
│   │   │   │   └── out/
│   │   │   │       └── persistance/     # Repositorios de base de datos (puntos de salida)
│   │   │   ├── application/             # Casos de uso
│   │   │   ├── commons/
│   │   │   │   ├── dto/                 # Objetos de transferencia de datos
│   │   │   │   └── mapper/              # Mapeadores de objetos
│   │   │   ├── config/                  # Clases de configuración
│   │   │   └── domain/                  # Modelo de dominio
│   │   └── resources/
│   │       ├── application.yml          # Configuración principal de la aplicación
│   │       └── application-local.yml    # Configuración del perfil local
│   └── test/
│       ├── java/com/example/demo/
│       │   ├── integration/             # Tests de integración con Cucumber
│       │   └── unit/                    # Tests unitarios
│       └── resources/
│           ├── features/                # Archivos de características de Cucumber
│           └── application-test.yml     # Configuración del perfil de test
└── pom.xml                              # Configuración de Maven
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

## Licencia

Este proyecto es privado y solo para fines demostrativos.
