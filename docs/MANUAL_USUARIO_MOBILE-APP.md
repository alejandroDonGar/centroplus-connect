# CentroPlus Connect · Aplicación de Escritorio JavaFX

# 1. Introducción

CentroPlus Connect es una aplicación de escritorio desarrollada utilizando JavaFX cuyo objetivo principal es facilitar la gestión de usuarios, actividades, reservas e incidencias dentro de un entorno educativo.

La aplicación permite centralizar toda la información relacionada con el funcionamiento del centro, proporcionando una interfaz gráfica intuitiva que simplifica tanto la consulta como la administración de los distintos recursos disponibles.

El sistema ha sido diseñado siguiendo una arquitectura basada en capas, separando claramente la interfaz de usuario, la lógica de negocio y el acceso a datos.

Esta separación permite mejorar la mantenibilidad del código, facilitar futuras ampliaciones y garantizar una mejor organización del proyecto.

---

# 2. Objetivos del proyecto

El desarrollo de la aplicación persigue diversos objetivos funcionales y técnicos.

## Objetivo general

Proporcionar una herramienta de gestión sencilla y eficiente que permita administrar los principales procesos del centro desde una aplicación de escritorio.

## Objetivos específicos

* Gestionar usuarios registrados.
* Gestionar actividades académicas y deportivas.
* Controlar reservas realizadas por los usuarios.
* Registrar incidencias y realizar su seguimiento.
* Mantener la información almacenada en una base de datos relacional.
* Proporcionar una interfaz gráfica amigable.
* Aplicar principios de programación orientada a objetos.
* Facilitar el mantenimiento y evolución futura del sistema.

---

# 3. Tecnologías utilizadas

## Java 17

Todo el proyecto ha sido desarrollado utilizando Java como lenguaje principal.

Java proporciona:

* Portabilidad.
* Robustez.
* Orientación a objetos.
* Amplio ecosistema de librerías.

---

## JavaFX

JavaFX constituye la tecnología principal utilizada para la construcción de la interfaz gráfica.

Entre sus principales ventajas destacan:

* Separación entre diseño y lógica.
* Integración mediante FXML.
* Sistema de estilos CSS.
* Amplio conjunto de componentes visuales.

Gracias a JavaFX la aplicación ofrece una experiencia visual moderna y fácilmente personalizable.

---

## Scene Builder

Scene Builder ha sido utilizado para diseñar las distintas pantallas de forma visual.

Esta herramienta permite:

* Diseñar ventanas.
* Organizar componentes.
* Configurar eventos.
* Generar archivos FXML automáticamente.

---

## SQLite

La persistencia de datos se realiza mediante SQLite.

SQLite es una base de datos ligera que almacena toda la información en un único fichero.

Entre sus ventajas destacan:

* No requiere instalación de servidor.
* Fácil distribución.
* Alto rendimiento para aplicaciones de escritorio.
* Simplicidad de mantenimiento.

---

## Maven

Maven se utiliza como herramienta de construcción del proyecto.

Permite:

* Gestionar dependencias.
* Compilar el proyecto.
* Ejecutar pruebas.
* Generar artefactos.

---

# 4. Arquitectura general

La aplicación ha sido organizada siguiendo una arquitectura por capas.

```text
Interfaz JavaFX
       │
       ▼
Controllers
       │
       ▼
Services
       │
       ▼
Repositories
       │
       ▼
SQLite
```

Cada capa tiene una responsabilidad específica.

---

## Capa de presentación

La capa de presentación está formada por:

```text
FXML
CSS
Controllers
```

Su función es interactuar con el usuario.

Entre sus responsabilidades se encuentran:

* Mostrar información.
* Recoger datos introducidos por el usuario.
* Gestionar eventos de la interfaz.

---

## Capa de servicios

La capa de servicios contiene la lógica de negocio.

Entre sus responsabilidades destacan:

* Validación de información.
* Aplicación de reglas del sistema.
* Coordinación entre controladores y repositorios.

Esta capa evita que la interfaz acceda directamente a la base de datos.

---

## Capa de repositorios

Los repositorios son responsables de la persistencia.

Sus funciones principales son:

* Realizar consultas SQL.
* Insertar registros.
* Actualizar información.
* Eliminar registros.

La aplicación encapsula todas las operaciones de base de datos dentro de esta capa.

---

# 5. Estructura del proyecto

La aplicación se encuentra organizada en los siguientes paquetes:

```text
src/main/java/es/ies/puerto
```

---

## Controllers

```text
controllers
```

Gestionan la comunicación entre la interfaz gráfica y la lógica de negocio.

Cada ventana dispone de su propio controlador.

Los controladores reciben los eventos generados por los usuarios y coordinan las operaciones necesarias para responder a dichas acciones.

---

## Modelos

```text
modelos
```

Representan las entidades principales del sistema.

Actualmente el proyecto incluye:

```text
Actividad
Usuario
Reserva
Incidencia
```

Estas clases contienen la información asociada a cada concepto de negocio.

---

## Services

```text
services
```

Implementan la lógica de negocio de la aplicación.

Esta capa se encarga de procesar la información antes de enviarla a los repositorios.

---

## Repositories

```text
repositories
```

Gestionan el acceso a la base de datos SQLite.

Todos los accesos a datos pasan por esta capa.

---

## Utils

```text
utils
```

Contiene clases auxiliares utilizadas por distintos módulos del sistema.

Su objetivo es evitar duplicación de código y centralizar funcionalidades comunes.
