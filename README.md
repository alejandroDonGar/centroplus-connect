# CentroPlus Connect

![Banner](imgs/banner_proyecto.png)

CentroPlus Connect es una solución de gestión desarrollada para centros educativos que permite administrar usuarios, actividades, reservas e incidencias mediante una aplicación de escritorio desarrollada con JavaFX y una API REST construida con Spring Boot.

El proyecto ha sido diseñado aplicando buenas prácticas de ingeniería del software, utilizando una arquitectura desacoplada, persistencia de datos relacional y documentación automática mediante Swagger/OpenAPI.

---

## Vista previa

### Aplicación JavaFX

![Aplicación JavaFX](imgs/pagina_principal_aplicacion.png)

### API REST documentada con Swagger

![Swagger](imgs/swagger_integrado.png)

---

## Características destacadas

✅ Aplicación de escritorio desarrollada con JavaFX

✅ API REST desarrollada con Spring Boot

✅ Arquitectura Hexagonal en el backend

✅ Persistencia mediante SQLite y H2 Database

✅ CRUD completo para usuarios, actividades, reservas e incidencias

✅ Documentación automática con Swagger/OpenAPI

✅ Testing unitario con JUnit 5 y Mockito

✅ Conversión automática de modelos mediante MapStruct

---

## Funcionalidades principales

### Gestión de usuarios

* Alta de usuarios.
* Consulta de usuarios.
* Modificación de usuarios.
* Eliminación de usuarios.

### Gestión de actividades

* Creación de actividades.
* Consulta de actividades disponibles.
* Gestión de plazas.
* Actualización de actividades.
* Eliminación de actividades.

### Gestión de reservas

* Creación de reservas.
* Consulta de reservas.
* Actualización de reservas.
* Cancelación de reservas.

### Gestión de incidencias

* Registro de incidencias.
* Seguimiento del estado.
* Modificación de incidencias.
* Resolución y cierre.

---

## Arquitectura del proyecto

El repositorio se encuentra organizado en tres módulos principales:

```text
CentroPlus Connect
│
├── backend-api
│
├── mobile-app
│
├── database
│
├── docs
│
└── imgs
```

### Backend API

API REST desarrollada utilizando Spring Boot.

Arquitectura:

```text
Controller
    ↓
Service
    ↓
Port
    ↓
Persistence Adapter
    ↓
Repository
    ↓
Database
```

### Aplicación JavaFX

Aplicación de escritorio desarrollada utilizando JavaFX.

Arquitectura:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
SQLite
```

### Database

Contiene la estructura compartida de la base de datos.

```text
database
├── schema.sql
└── seed.sql
```

Estos scripts permiten recrear la estructura y los datos iniciales utilizados por la aplicación.

---

## Tecnologías utilizadas

| Tecnología      | Función                  |
| --------------- | ------------------------ |
| Java 17         | Lenguaje principal       |
| JavaFX          | Interfaz gráfica         |
| Spring Boot     | Backend REST             |
| Spring Data JPA | Persistencia             |
| SQLite          | Base de datos local      |
| H2 Database     | Base de datos en memoria |
| MapStruct       | Conversión DTO ↔ Modelo  |
| Swagger/OpenAPI | Documentación REST       |
| Maven           | Gestión de dependencias  |
| JUnit 5         | Testing unitario         |
| Mockito         | Mocking                  |
| CSS             | Personalización visual   |
| FXML            | Diseño de interfaces     |

---

## Instalación y ejecución

### Requisitos previos

* Java 17 o superior
* Maven 3.9 o superior
* Git

### Clonar repositorio

```bash
git clone https://github.com/alejandroDonGar/centroplus-connect.git
cd centroplus-connect
```

---

## Ejecución de la aplicación JavaFX

```bash
cd mobile-app
mvn clean javafx:run
```

La aplicación iniciará utilizando SQLite como sistema de persistencia local.

---

## Ejecución de la API REST

```bash
cd backend-api
mvn spring-boot:run
```

Por defecto la API estará disponible en:

```text
http://localhost:8080
```

---

## Documentación Swagger

Una vez iniciada la API REST, la documentación interactiva estará disponible en:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger permite:

* Consultar todos los endpoints disponibles.
* Realizar peticiones directamente desde el navegador.
* Visualizar modelos de datos.
* Probar operaciones CRUD sin herramientas externas.

---

## Testing

El proyecto incluye pruebas unitarias para garantizar la calidad y estabilidad del sistema.

Ejecutar todas las pruebas:

```bash
mvn test
```

Cobertura principal:

* DTOs
* Entidades
* Servicios
* Mappers
* Adaptadores de persistencia
* Controladores REST

Tecnologías utilizadas:

* JUnit 5
* Mockito

---

## Documentación adicional

La documentación detallada del proyecto se encuentra disponible en:

```text
docs/
├── manual-backend-api.md
└── manual-mobile-app.md
```

---

## Autor

### Alejandro Donate García

🔗 GitHub

https://github.com/alejandroDonGar

IES Puerto de la Cruz

Proyecto Final DAM · CentroPlus Connect

---

## Estado del proyecto

✅ Aplicación JavaFX funcional

✅ API REST funcional

✅ CRUD completo de usuarios

✅ CRUD completo de actividades

✅ CRUD completo de reservas

✅ CRUD completo de incidencias

✅ Arquitectura Hexagonal implementada

✅ Swagger/OpenAPI integrado

✅ Persistencia mediante SQLite y H2

✅ Tests unitarios implementados

✅ Proyecto finalizado