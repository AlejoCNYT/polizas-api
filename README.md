 Polizas API — Spring Boot Technical Challenge

API REST desarrollada en **Spring Boot** para la gestión de pólizas de arrendamiento, incluyendo creación, renovación, cancelación y gestión de riesgos asociados.

REST API built with **Spring Boot** for managing rental insurance policies, including creation, renewal, cancellation, and associated risk management.

---

# Arquitectura / Architecture

<img width="1919" height="1076" alt="Captura de pantalla 2026-03-07 131914" src="https://github.com/user-attachments/assets/38018d8e-791b-41b2-be3f-7cb2a2fd6f28" />

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

<img width="506" height="203" alt="Captura de pantalla 2026-03-07 123117" src="https://github.com/user-attachments/assets/6001c5cb-f386-46f2-8b5d-6fe1c65e475b" />
<img width="364" height="186" alt="Captura de pantalla 2026-03-07 124500" src="https://github.com/user-attachments/assets/5608a0c1-f0e4-4454-bac0-3df66777da45" />
<img width="1585" height="633" alt="Captura de pantalla 2026-03-07 124302" src="https://github.com/user-attachments/assets/d7181949-e9a8-4eff-b219-c033c048dd51" />

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

<img width="1650" height="627" alt="Captura de pantalla 2026-03-07 124309" src="https://github.com/user-attachments/assets/69d8dc9a-eaec-41af-9772-87d53cc98cc8" />

---

## Cancelar póliza

```
POST /polizas/{id}/cancelar
```

<img width="1107" height="668" alt="Captura de pantalla 2026-03-07 124318" src="https://github.com/user-attachments/assets/41de4734-ef4b-4676-876a-b1f8f6d3ac48" />

---

## Agregar riesgo a una póliza

```
POST /polizas/{id}/riesgos
```

<img width="1681" height="602" alt="Captura de pantalla 2026-03-07 125452" src="https://github.com/user-attachments/assets/4cbb0c6a-bbd2-4ed3-b7c3-06a99416e508" />

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

<img width="942" height="874" alt="Captura de pantalla 2026-03-07 131157" src="https://github.com/user-attachments/assets/6d6a56ad-e7a3-4ec8-96df-9ec748ef4800" />

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

<img width="1883" height="949" alt="Captura de pantalla 2026-03-07 125605" src="https://github.com/user-attachments/assets/9832558b-fa74-4e2f-99e7-005203d2fcd8" />
<img width="1892" height="1048" alt="Captura de pantalla 2026-03-07 125614" src="https://github.com/user-attachments/assets/68509c32-35cb-4f1e-9bb7-8df1c022f914" />

### macOS / Linux

```
./mvnw spring-boot:run
```

API disponible en:

```
http://localhost:8081
```

<img width="496" height="177" alt="imagen" src="https://github.com/user-attachments/assets/d6fc142f-cc1d-4602-bbdf-cc125b28424f" />

---

# Ejecutar con Docker

## Construir imagen

```
docker build -t polizas-api .
```

<img width="1222" height="581" alt="Captura de pantalla 2026-03-07 142342" src="https://github.com/user-attachments/assets/8e71b433-f18f-41ff-8841-5e1de11c03c9" />

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

<img width="583" height="491" alt="Captura de pantalla 2026-03-07 123854" src="https://github.com/user-attachments/assets/64429618-73a8-4a39-ba88-54983e10fd76" />

---

# Estructura del Proyecto

```

## 📂 Estructura del Proyecto

```text
POLIZAS-API
├── 📂 src
│   ├── 📂 main
│   │   ├── 📂 java
│   │   │   └── 📂 com.polizas
│   │   │       ├── 📂 controller
│   │   │       │   ├── CoreMockController.java
│   │   │       │   ├── HealthController.java
│   │   │       │   └── PolizaController.java
│   │   │       ├── 📂 model
│   │   │       │   ├── Poliza.java
│   │   │       │   └── Riesgo.java
│   │   │       ├── 📂 repository
│   │   │       │   ├── PolizaRepository.java
│   │   │       │   └── RiesgoRepository.java
│   │   │       ├── 📂 security
│   │   │       ├── 📂 service
│   │   │       │   └── PolizaService.java
│   │   │       └── PolizasApiApplication.java
│   │   └── 📂 resources
│   │       ├── 📂 static
│   │       ├── 📂 templates
│   │       └── application.properties
│   └── 📂 test
├── 📂 target
├── .gitattributes
├── .gitignore
├── Dockerfile
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml


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

<img width="554" height="261" alt="Captura de pantalla 2026-03-07 133308" src="https://github.com/user-attachments/assets/c13a7b99-29f3-47d9-b6ad-b90a5e923395" />

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
