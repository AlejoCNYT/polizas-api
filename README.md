# Polizas API — Spring Boot Technical Challenge

API REST desarrollada en **Spring Boot** para la gestión de pólizas de arrendamiento, incluyendo creación, renovación, cancelación y gestión de riesgos asociados.

REST API built with **Spring Boot** for managing rental insurance policies, including creation, renewal, cancellation, and associated risk management.

---

# Arquitectura / Architecture

- Java 21
- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- Maven
- Docker
- Swagger / OpenAPI

La API sigue una arquitectura simple basada en:

```
Controller → Service → Repository → Database
```

---

# Modelo de Dominio / Domain Model

## Poliza

| Campo | Tipo |
|------|------|
| id | Long |
| tipo | String |
| estado | String |
| canonMensual | double |
| prima | double |

Estados posibles:

```
ACTIVA
RENOVADA
CANCELADA
```

---

## Riesgo

| Campo | Tipo |
|------|------|
| id | Long |
| direccion | String |
| arrendatario | String |

Relación:

```
Poliza 1 ---- N Riesgos
```

---

# Endpoints

## Crear póliza

```
POST /polizas
```

Ejemplo request:

```json
{
  "tipo": "INDIVIDUAL",
  "canonMensual": 1000,
  "prima": 200
}
```

---

## Listar pólizas

```
GET /polizas
```

---

## Obtener póliza por id

```
GET /polizas/{id}
```

---

## Renovar póliza

Aplica incremento del **IPC (10%)**.

```
POST /polizas/{id}/renovar
```

---

## Cancelar póliza

```
POST /polizas/{id}/cancelar
```

---

## Agregar riesgo a una póliza

```
POST /polizas/{id}/riesgos
```

Ejemplo request:

```json
{
  "direccion": "Calle 123",
  "arrendatario": "Juan Perez"
}
```

---

# Swagger / API Documentation

Disponible en:

```
http://localhost:8081/swagger-ui/index.html
```

Swagger permite probar todos los endpoints desde el navegador.

---

# Ejecución Local / Local Execution

## Requisitos / Requirements

- Java 21
- Maven
- Docker (optional)

---

## Ejecutar con Maven

### Windows

```
mvnw spring-boot:run
```

### macOS / Linux

```
./mvnw spring-boot:run
```

API disponible en:

```
http://localhost:8081
```

---

# Ejecutar con Docker

## Construir imagen

```
docker build -t polizas-api .
```

---

## Ejecutar contenedor

```
docker run -p 8081:8081 polizas-api
```

---

# Base de Datos

La aplicación utiliza **H2 In-Memory Database**.

Console:

```
http://localhost:8081/h2-console
```

---

# Estructura del Proyecto

```
src
 ├── controller
 │    └── PolizaController
 │
 ├── service
 │    └── PolizaService
 │
 ├── repository
 │    └── PolizaRepository
 │
 ├── model
 │    ├── Poliza
 │    └── Riesgo
 │
 └── PolizasApiApplication
```

---

# Lógica de Negocio

### Renovación de póliza

La renovación incrementa los valores según IPC:

```
canonMensual = canonMensual * (1 + ipc)
prima = prima * (1 + ipc)
```

Donde:

```
ipc = 0.10
```

---

# Docker

El proyecto incluye un **Dockerfile** para ejecutar la API en contenedor.

```
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/polizas-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
```

---

# Autor / Author

Daniel Alejandro Acero Varela

System Engineer  
Backend Developer

GitHub:

```
https://github.com/AlejoCNYT
```

---

# Notas

Este proyecto fue desarrollado como parte de una **prueba técnica backend** enfocada en:

- diseño de APIs REST
- modelado de dominio
- lógica de negocio
- persistencia con JPA
- contenedores Docker
