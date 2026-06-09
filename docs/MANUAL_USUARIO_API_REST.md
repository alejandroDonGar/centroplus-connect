# CentroPlus API REST

## 1. Introducción

CentroPlus API REST es una aplicación desarrollada utilizando Spring Boot cuyo objetivo principal es proporcionar una interfaz de comunicación entre los clientes del sistema CentroPlus Connect y la capa de persistencia de datos.

La API permite gestionar de forma centralizada toda la información relacionada con usuarios, actividades, reservas e incidencias, ofreciendo operaciones CRUD completas (Create, Read, Update y Delete) para cada una de estas entidades.

El proyecto ha sido desarrollado siguiendo los principios de la Arquitectura Hexagonal (Ports and Adapters), permitiendo una clara separación de responsabilidades entre las diferentes capas de la aplicación y favoreciendo la mantenibilidad, escalabilidad y capacidad de prueba del sistema.

Además, se han incorporado herramientas modernas como Spring Data JPA para la persistencia, MapStruct para la conversión entre modelos y entidades, Swagger/OpenAPI para la documentación automática de los endpoints y JUnit junto con Mockito para la realización de pruebas unitarias.

---

# 2. Objetivos del proyecto

La API REST de CentroPlus persigue los siguientes objetivos:

## Objetivo general

Desarrollar un servicio backend robusto y mantenible que permita la gestión integral de la información del centro mediante una arquitectura desacoplada y fácilmente extensible.

## Objetivos específicos

* Gestionar usuarios registrados dentro del sistema.
* Administrar las actividades ofertadas por el centro.
* Gestionar reservas realizadas por los usuarios.
* Registrar y controlar incidencias comunicadas por los usuarios.
* Exponer una interfaz REST accesible desde aplicaciones cliente.
* Mantener una arquitectura limpia basada en principios SOLID.
* Facilitar la realización de pruebas unitarias.
* Proporcionar documentación automática mediante Swagger.

---

# 3. Tecnologías utilizadas

El proyecto ha sido desarrollado utilizando las siguientes tecnologías:

## Java 17

Lenguaje principal utilizado para el desarrollo de toda la aplicación.

Java proporciona robustez, orientación a objetos y una amplia integración con el ecosistema Spring.

## Spring Boot 3

Framework principal utilizado para simplificar la configuración y despliegue de la aplicación.

Entre sus ventajas destacan:

* Configuración automática.
* Integración con Spring Data.
* Integración con Spring MVC.
* Gestión de dependencias.
* Arranque embebido mediante Tomcat.

## Spring Data JPA

Permite abstraer gran parte de las operaciones de persistencia mediante repositorios basados en interfaces.

Gracias a esta tecnología se evita la escritura manual de consultas SQL para las operaciones CRUD más habituales.

## H2 Database

Base de datos relacional utilizada durante el desarrollo y las pruebas.

La información se carga automáticamente mediante scripts SQL definidos en el proyecto.

## MapStruct

Framework utilizado para realizar conversiones entre:

* Entidades JPA
* Modelos de dominio

Esto evita la escritura repetitiva de código de transformación.

## Swagger / OpenAPI

Permite generar documentación interactiva de todos los endpoints REST disponibles.

Gracias a Swagger es posible probar cada endpoint directamente desde el navegador.

## Maven

Herramienta de gestión y construcción del proyecto.

Se utiliza para:

* Gestión de dependencias.
* Compilación.
* Ejecución de pruebas.
* Empaquetado de la aplicación.

## JUnit 5 y Mockito

Utilizados para implementar las pruebas unitarias del sistema.

JUnit proporciona la infraestructura de testing mientras que Mockito permite la simulación de dependencias mediante mocks.

# 4. Arquitectura Hexagonal

La aplicación ha sido desarrollada siguiendo el patrón de Arquitectura Hexagonal, también conocido como Ports and Adapters.

Este enfoque arquitectónico busca desacoplar la lógica de negocio de las tecnologías concretas utilizadas para la entrada y salida de información.

De esta forma, la lógica principal del sistema permanece independiente de aspectos como:

* Bases de datos.
* Frameworks.
* Interfaces gráficas.
* APIs externas.
* Sistemas de mensajería.

La principal ventaja de esta arquitectura es que facilita la evolución del sistema a largo plazo, permitiendo sustituir tecnologías concretas sin modificar la lógica de negocio.

## Flujo de funcionamiento

Cuando un cliente realiza una petición HTTP, ésta sigue el siguiente recorrido:

```text
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Port
   │
   ▼
Persistence Adapter
   │
   ▼
Repository JPA
   │
   ▼
Base de Datos
```

La respuesta recorre el camino inverso hasta llegar nuevamente al cliente.

Este desacoplamiento permite que la lógica de negocio pueda mantenerse estable incluso si en el futuro se sustituye la base de datos H2 por MySQL, PostgreSQL o cualquier otro sistema de persistencia.

---

# 5. Estructura del proyecto

El proyecto se encuentra organizado en distintos paquetes siguiendo las responsabilidades de cada capa.

## Dominio

```text
domain/model
```

Contiene las entidades principales del sistema.

```text
Actividad
Usuario
Reserva
Incidencia
```

Estas clases representan los conceptos fundamentales del negocio y no dependen de ninguna tecnología concreta.

Por ejemplo:

* Una Actividad representa una actividad ofertada por el centro.
* Un Usuario representa una persona registrada en el sistema.
* Una Reserva representa la inscripción de un usuario en una actividad.
* Una Incidencia representa una comunicación realizada por un usuario.

Las entidades del dominio constituyen el núcleo de la aplicación.

---

## Business

```text
business
├── ports
└── services
```

### Ports

Los puertos definen contratos que especifican qué operaciones puede realizar la lógica de negocio.

Ejemplos:

```text
ActividadServicePort
UsuarioServicePort
ReservaServicePort
IncidenciaServicePort
```

Estos contratos permiten que los servicios trabajen sin conocer detalles de implementación.

### Services

Los servicios implementan la lógica de negocio del sistema.

Entre sus responsabilidades se encuentran:

* Validación de datos.
* Gestión de operaciones CRUD.
* Coordinación entre diferentes componentes.
* Aplicación de reglas de negocio.

Ejemplos:

```text
ActividadService
UsuarioService
ReservaService
IncidenciaService
```

---

## Adaptadores de entrada

```text
adapters/in
```

Permiten que sistemas externos interactúen con la aplicación.

### API

```text
adapters/in/api
```

Contiene los objetos utilizados para intercambiar información mediante JSON.

Ejemplos:

```text
ActividadRequest
ActividadResponse

UsuarioRequest
UsuarioResponse
```

Estos DTOs evitan exponer directamente los modelos internos.

### Controllers

```text
adapters/in/controlador
```

Gestionan las peticiones HTTP recibidas.

Ejemplos:

```text
ActividadController
UsuarioController
ReservaController
IncidenciaController
HealthController
```

Cada controlador se encarga de:

* Recibir peticiones.
* Invocar la lógica de negocio.
* Transformar resultados.
* Generar respuestas HTTP.

---

## Adaptadores de salida

```text
adapters/out/persistencia
```

Implementan el acceso a la base de datos.

Cada módulo dispone de:

```text
JpaEntity
JpaRepository
PersistenceAdapter
```

Por ejemplo:

```text
ActividadJpaEntity
ActividadJpaRepository
ActividadPersistenceAdapter
```

El adaptador traduce las operaciones de negocio a operaciones concretas sobre la base de datos.

---

## Mappers

```text
adapters/mapper
```

Los mappers son responsables de transformar objetos entre distintas capas.

Ejemplos:

```text
ActividadMapper
UsuarioMapper
ReservaMapper
IncidenciaMapper
```

Su utilización evita duplicar código de conversión y mejora la mantenibilidad.

---

# 6. Modelo de dominio

El modelo de dominio representa los conceptos centrales del sistema CentroPlus.

## Actividad

Representa una actividad ofertada por el centro.

Información almacenada:

* Identificador.
* Nombre.
* Tipo de actividad.
* Duración.
* Precio.
* Plazas máximas.
* Plazas ocupadas.

Además, el sistema calcula automáticamente las plazas disponibles.

---

## Usuario

Representa una persona registrada dentro de la plataforma.

Información almacenada:

* Identificador.
* Nombre.
* DNI.
* Correo electrónico.
* Teléfono.
* Tipo de usuario.

Los usuarios pueden realizar reservas y generar incidencias.

---

## Reserva

Representa la inscripción de un usuario en una actividad.

Información almacenada:

* Identificador.
* Usuario asociado.
* Actividad asociada.
* Fecha.
* Estado.

El estado permite conocer la situación actual de la reserva.

---

## Incidencia

Representa una comunicación realizada por un usuario.

Información almacenada:

* Identificador.
* Usuario asociado.
* Asunto.
* Descripción.
* Fecha.
* Estado.

Las incidencias permiten registrar problemas, sugerencias o consultas relacionadas con el funcionamiento del centro.

---

# 7. Persistencia y acceso a datos

La persistencia se implementa mediante Spring Data JPA.

Esta tecnología proporciona una capa de abstracción que simplifica enormemente el acceso a la base de datos.

Los repositorios extienden las interfaces proporcionadas por Spring, obteniendo automáticamente operaciones CRUD básicas.

Por ejemplo:

```java
public interface ActividadJpaRepository
       extends JpaRepository<ActividadJpaEntity, Long> {
}
```

Gracias a ello es posible realizar operaciones como:

* Guardar registros.
* Buscar por identificador.
* Obtener listados completos.
* Eliminar registros.

sin necesidad de escribir consultas SQL manuales.

Esta aproximación reduce la cantidad de código y mejora la mantenibilidad del proyecto.

La aplicación utiliza actualmente una base de datos H2 en memoria, cargando información inicial mediante el archivo:

```text
src/main/resources/data.sql
```

Esto permite disponer automáticamente de datos de prueba cada vez que se inicia la aplicación.

# 8. Documentación de la API mediante Swagger

Uno de los objetivos principales del proyecto ha sido facilitar la utilización y mantenimiento de la API REST.

Para ello se ha incorporado Swagger/OpenAPI, una herramienta que permite generar automáticamente documentación interactiva a partir de los controladores desarrollados en Spring Boot.

Gracias a Swagger, cualquier desarrollador puede consultar todos los endpoints disponibles sin necesidad de revisar el código fuente.

Además, la herramienta permite ejecutar peticiones reales directamente desde el navegador.

## Acceso a Swagger UI

Una vez iniciada la aplicación, la documentación puede consultarse mediante la siguiente dirección:

```text
http://localhost:8080/swagger-ui/index.html
```

Desde esta interfaz es posible:

* Consultar todos los endpoints disponibles.
* Visualizar los parámetros requeridos.
* Ejecutar peticiones GET, POST, PATCH y DELETE.
* Analizar las respuestas generadas por el sistema.
* Comprobar códigos de estado HTTP.

Esta funcionalidad resulta especialmente útil durante las fases de desarrollo, pruebas y mantenimiento.

---

## Documento OpenAPI

Además de la interfaz gráfica, Swagger genera automáticamente la especificación OpenAPI en formato JSON.

```text
http://localhost:8080/v3/api-docs
```

Este documento puede utilizarse para:

* Integración con otras aplicaciones.
* Generación automática de clientes REST.
* Herramientas de testing.
* Sistemas de documentación externos.

---

# 9. Endpoints disponibles

La API proporciona operaciones CRUD completas para las cuatro entidades principales del sistema.

---

## Gestión de actividades

Permite administrar todas las actividades ofertadas por el centro.

### Obtener todas las actividades

```http
GET /api/v1/actividades
```

Devuelve un listado completo de actividades registradas.

Ejemplo de respuesta:

```json
[
  {
    "id": 1,
    "nombre": "Yoga",
    "tipoActividad": "DEPORTIVA",
    "duracion": 60,
    "precio": 25.50,
    "plazasMaximas": 15,
    "plazasOcupadas": 8,
    "plazasDisponibles": 7
  }
]
```

### Obtener una actividad

```http
GET /api/v1/actividades/{id}
```

Permite recuperar una actividad concreta mediante su identificador.

### Crear actividad

```http
POST /api/v1/actividades
```

Permite registrar una nueva actividad.

### Actualizar actividad

```http
PATCH /api/v1/actividades/{id}
```

Actualiza parcialmente los datos de una actividad existente.

### Eliminar actividad

```http
DELETE /api/v1/actividades/{id}
```

Elimina una actividad del sistema.

---

## Gestión de usuarios

Permite administrar todos los usuarios registrados.

### Obtener usuarios

```http
GET /api/v1/usuarios
```

Obtiene el listado completo de usuarios.

### Obtener usuario por identificador

```http
GET /api/v1/usuarios/{id}
```

Devuelve la información de un usuario específico.

### Crear usuario

```http
POST /api/v1/usuarios
```

Registra un nuevo usuario.

### Actualizar usuario

```http
PATCH /api/v1/usuarios/{id}
```

Actualiza los datos de un usuario existente.

### Eliminar usuario

```http
DELETE /api/v1/usuarios/{id}
```

Elimina un usuario del sistema.

---

## Gestión de reservas

Permite controlar la inscripción de usuarios en actividades.

### Obtener reservas

```http
GET /api/v1/reservas
```

Obtiene el listado completo de reservas.

### Obtener reserva

```http
GET /api/v1/reservas/{id}
```

Recupera una reserva concreta.

### Crear reserva

```http
POST /api/v1/reservas
```

Registra una nueva reserva.

### Actualizar reserva

```http
PATCH /api/v1/reservas/{id}
```

Actualiza una reserva existente.

### Eliminar reserva

```http
DELETE /api/v1/reservas/{id}
```

Elimina una reserva del sistema.

---

## Gestión de incidencias

Permite gestionar problemas, sugerencias o consultas realizadas por los usuarios.

### Obtener incidencias

```http
GET /api/v1/incidencias
```

Obtiene el listado completo de incidencias registradas.

### Obtener incidencia

```http
GET /api/v1/incidencias/{id}
```

Recupera una incidencia concreta.

### Crear incidencia

```http
POST /api/v1/incidencias
```

Registra una nueva incidencia.

### Actualizar incidencia

```http
PATCH /api/v1/incidencias/{id}
```

Permite modificar una incidencia existente.

### Eliminar incidencia

```http
DELETE /api/v1/incidencias/{id}
```

Elimina una incidencia del sistema.

---

## Endpoint de estado

La API incorpora un endpoint destinado a verificar que la aplicación se encuentra funcionando correctamente.

```http
GET /api/v1/health
```

Respuesta esperada:

```json
{
  "status": "UP"
}
```

Este endpoint suele utilizarse durante procesos de monitorización y despliegue.

---

# 10. Estrategia de testing

La calidad del software ha sido una prioridad durante el desarrollo del proyecto.

Por este motivo se han implementado pruebas unitarias para verificar el comportamiento de los distintos componentes del sistema.

Las pruebas han sido desarrolladas utilizando:

* JUnit 5.
* Mockito.

---

## Objetivos de las pruebas

Las pruebas permiten verificar:

* Correcto funcionamiento de la lógica de negocio.
* Comportamiento esperado de los controladores.
* Funcionamiento de los adaptadores de persistencia.
* Correcta transformación realizada por los mappers.
* Integridad de los modelos de dominio.

---

## Cobertura implementada

Se han desarrollado pruebas para los siguientes componentes:

### Modelos de dominio

```text
Actividad
Usuario
Reserva
Incidencia
```

### DTOs

```text
Request
Response
```

### Servicios

```text
ActividadService
UsuarioService
ReservaService
IncidenciaService
```

### Adaptadores de persistencia

```text
PersistenceAdapter
```

### Mappers

```text
MapStruct Mappers
```

### Controladores

```text
REST Controllers
```

---

## Ejecución de las pruebas

Para ejecutar todas las pruebas del sistema:

```bash
mvn test
```

Una ejecución correcta mostrará:

```text
BUILD SUCCESS
```

garantizando que todos los componentes continúan funcionando correctamente tras cualquier modificación del código.

# 11. Base de datos

La API utiliza actualmente una base de datos H2 en memoria durante el desarrollo y las pruebas.

Esta elección permite disponer de un entorno ligero, rápido y fácilmente reproducible sin necesidad de instalar servidores externos.

Cada vez que la aplicación se inicia, la base de datos se genera automáticamente y se cargan los datos iniciales definidos en el proyecto.

## Configuración

La configuración principal se encuentra en:

```text
src/main/resources/application.properties
```

Desde este fichero es posible modificar:

* Puerto de ejecución.
* Configuración de Hibernate.
* Consola H2.
* Inicialización de datos.
* Nivel de logging.

## Datos precargados

La información inicial se encuentra definida en:

```text
src/main/resources/data.sql
```

Este archivo incorpora registros de prueba para:

* Usuarios.
* Actividades.
* Reservas.
* Incidencias.

Gracias a ello, la API puede comenzar a utilizarse inmediatamente después de arrancar la aplicación.

## Relación con la aplicación JavaFX

Uno de los objetivos del proyecto CentroPlus Connect ha sido mantener una coherencia entre la aplicación de escritorio y la API REST.

Por este motivo, ambas aplicaciones comparten la misma estructura lógica de datos.

La versión JavaFX utiliza SQLite mediante los archivos:

```text
database/schema.sql
database/seed.sql
```

Mientras que la API REST utiliza entidades JPA equivalentes sobre H2.

Esta estrategia facilita futuras migraciones hacia una arquitectura completamente cliente-servidor.

---

# 12. Ejecución y despliegue

## Compilación

Para compilar el proyecto:

```bash
mvn clean compile
```

Durante este proceso Maven:

* Descarga dependencias.
* Genera código MapStruct.
* Compila todas las clases.
* Verifica la estructura del proyecto.

Si la compilación finaliza correctamente se mostrará:

```text
BUILD SUCCESS
```

---

## Ejecución local

Para iniciar la aplicación:

```bash
mvn spring-boot:run
```

Spring Boot realizará automáticamente:

1. Inicialización del contenedor Tomcat embebido.
2. Configuración de Spring.
3. Creación de la base de datos H2.
4. Carga de los datos iniciales.
5. Publicación de los endpoints REST.

Una vez completado el proceso la aplicación quedará accesible desde:

```text
http://localhost:8080
```

---

## Verificación del sistema

Puede verificarse el correcto funcionamiento utilizando:

```text
http://localhost:8080/swagger-ui/index.html
```

o bien:

```text
GET /api/v1/health
```

---

# 13. Casos de uso principales

La API ha sido diseñada para dar soporte a las necesidades de gestión del centro.

A continuación se describen algunos escenarios habituales.

## Consulta de actividades

Un usuario desea conocer las actividades disponibles.

Proceso:

1. El cliente realiza una petición GET.
2. El controlador recibe la solicitud.
3. El servicio consulta los datos.
4. El adaptador recupera la información de la base de datos.
5. Se devuelve la lista de actividades.

Resultado:

El usuario obtiene un catálogo actualizado de actividades.

---

## Registro de una reserva

Un usuario desea inscribirse en una actividad.

Proceso:

1. El cliente envía una petición POST.
2. El controlador valida la solicitud.
3. El servicio procesa la reserva.
4. Se almacena en la base de datos.
5. Se devuelve la reserva creada.

Resultado:

La actividad queda asociada al usuario.

---

## Gestión de incidencias

Un usuario detecta un problema y desea comunicarlo.

Proceso:

1. Se crea una incidencia mediante POST.
2. El sistema registra la información.
3. La incidencia queda almacenada.
4. Puede actualizarse posteriormente mediante PATCH.

Resultado:

El centro dispone de un mecanismo formal para gestionar incidencias.

---

# 14. Ventajas de la arquitectura utilizada

La utilización de Arquitectura Hexagonal aporta numerosos beneficios.

## Desacoplamiento

La lógica de negocio no depende de la tecnología utilizada para la persistencia.

Esto permite sustituir H2 por MySQL o PostgreSQL sin modificar los servicios.

---

## Mantenibilidad

Cada componente tiene una responsabilidad claramente definida.

Esto simplifica:

* Corrección de errores.
* Evolución del sistema.
* Incorporación de nuevas funcionalidades.

---

## Escalabilidad

La arquitectura facilita la incorporación de:

* Nuevos endpoints.
* Nuevos adaptadores.
* Nuevas bases de datos.
* Sistemas externos.

---

## Testabilidad

La separación entre capas permite realizar pruebas unitarias independientes.

Cada componente puede verificarse de forma aislada mediante mocks y dobles de prueba.

---

# 15. Mejoras futuras

Aunque la aplicación cumple completamente los objetivos establecidos, existen diversas líneas de evolución posibles.

## Seguridad

Incorporación de:

* Spring Security.
* JWT.
* Control de roles.

---

## Persistencia externa

Sustitución de la base de datos H2 por:

* MySQL.
* PostgreSQL.
* MariaDB.

---

## Despliegue cloud

Publicación en servicios como:

* Railway.
* Render.
* AWS.
* Azure.

---

## Integración completa con JavaFX

Actualmente ambas aplicaciones comparten modelo de datos.

Una futura evolución permitiría que la aplicación JavaFX consumiese directamente la API REST mediante peticiones HTTP.

Esto transformaría el sistema en una arquitectura cliente-servidor completamente distribuida.

---

# 16. Conclusiones

El proyecto CentroPlus API REST ha permitido desarrollar una solución moderna para la gestión de usuarios, actividades, reservas e incidencias.

Durante el desarrollo se han aplicado tecnologías y patrones ampliamente utilizados en entornos profesionales como Spring Boot, JPA, MapStruct y Arquitectura Hexagonal.

La aplicación proporciona una API REST completamente funcional, documentada mediante Swagger y respaldada por pruebas unitarias que garantizan la calidad del código.

Además, la estructura modular del proyecto facilita futuras ampliaciones y convierte la solución en una base sólida para evolucionar hacia sistemas de mayor complejidad.

El resultado final es una plataforma mantenible, escalable y preparada para integrarse con aplicaciones cliente como la aplicación JavaFX desarrollada dentro del mismo proyecto CentroPlus Connect.
