# 🎓 Net_Academy v2.0: Sistema Integral de Gestión de Portafolios

![Banner](https://img.youtube.com/vi/k_3y3pPgyDU/hqdefault.jpg)
*(Captura del sistema funcionando)*

**Proyecto Integrador - Fase II (Backend, Seguridad & Integración)**
**Asignatura:** Programación y Plataformas Web
**Estado:** Finalizado 🚀

---

## 📑 Tabla de Contenidos

1. [Enlaces a Entregables](#1-enlaces-a-entregables-código-fuente)
2. [Información Institucional](#2-información-institucional)
3. [Resumen Ejecutivo](#3-resumen-ejecutivo)
4. [Arquitectura de Software](#4-arquitectura-de-software-full-stack)
5. [Ingeniería Backend](#5-ingeniería-y-desarrollo-backend)
6. [Integración Frontend](#6-integración-frontend-angular)
7. [Documentación API (REST)](#7-documentación-de-api-openapi)
8. [Video Demostrativo](#8-video-de-presentación-funcional)

---

## 1. Enlaces a Entregables (Código Fuente)

Cumpliendo con la arquitectura de microservicios y despliegue desacoplado, el código fuente se divide en:

### 🖥️ FRONTEND (Interfaz de Usuario)
* **Repositorio GitHub:** [Proyecto-Formulario (Angular)](https://github.com/ArielCalleSteven/Proyecto-Formulario)
* **Despliegue (Firebase):** [🔗 Ver App en Vivo](https://portafolio-calle-torres-2025.web.app/login)

### ⚙️ BACKEND (Lógica y Datos)
* **Repositorio GitHub:** [Backend_Proyecto (Spring Boot)](https://github.com/ArielCalleSteven/Backend_Proyecto)
* **Documentación API (Swagger):** [🔗 Ver Documentación REST](https://backendproyecto-production-1c31.up.railway.app/swagger-ui/index.html)

---

## 2. Información Institucional

| Categoría | Detalle |
| --- | --- |
| **Universidad** | Universidad Politécnica Salesiana - Sede Cuenca |
| **Carrera** | Computación / Ingeniería de Software |
| **Asignatura** | Programación y Plataformas Web |
| **Docente** | Ing. Cristian Timbi Sisalima |
| **Desarrolladores** | Ariel Calle & Juan Diego Torres |
| **Periodo** | Marzo 2024 – Agosto 2024 |

---

## 3. Resumen Ejecutivo

La evolución de **Net_Academy** hacia su versión 2.0 representa el salto de un prototipo a un sistema empresarial robusto. Se ha migrado a una arquitectura **Backend Monolítica** en Java con **Spring Boot**, garantizando integridad referencial mediante SQL y seguridad avanzada con **JWT**.

El sistema integra capacidades de **Business Intelligence** (Dashboards y Reportes PDF), notificaciones asíncronas vía correo electrónico y una interfaz moderna en Angular desplegada en la nube.

---

## 4. Arquitectura de Software (Full Stack)

El sistema utiliza una arquitectura en capas, comunicándose vía API REST.

### 🏗️ Stack Tecnológico
* **Backend:** Java 17, Spring Boot 3, Spring Security, Hibernate.
* **Frontend:** Angular 16+, TailwindCSS, DaisyUI.
* **Base de Datos:** MySQL (Producción en Railway).
* **Servicios Externos:** EmailJS (Notificaciones).

### 🔄 Flujo de Datos
`Cliente (Angular)` ➡ `Interceptor JWT` ➡ `Controller` ➡ `Service Layer` ➡ `Repository (JPA)` ➡ `Base de Datos`

---

## 5. Ingeniería y Desarrollo Backend

### 5.1. Persistencia de Datos
Migración de NoSQL a **Relacional (SQL)**. Se implementaron relaciones estrictas:
* `User` (1) ↔ (N) `Project`
* `Programmer` (N) ↔ (M) `Student` (Mediante tabla `Appointment`)

### 5.2. Seguridad (Spring Security & JWT)
* **Stateless Authentication:** Uso de JSON Web Tokens.
* **Role-Based Access Control (RBAC):**
    * `ROLE_ADMIN`: Acceso total a dashboards y reportes.
    * `ROLE_DEV`: Gestión de perfil y horarios propios.
* **Password Hashing:** BCryptPasswordEncoder.

### 5.3. Funcionalidades Clave
* **Reportes PDF:** Generación de archivos binarios (`Blob`) desde el backend para historiales de asesorías.
* **Notificaciones:** Envío de correos automáticos al confirmar/rechazar citas.
* **Validación de Horarios:** Lógica para evitar solapamiento de citas.

---

## 6. Integración Frontend (Angular)

El cliente web consume la API REST y gestiona la experiencia de usuario:
* **Guards:** Protección de rutas (`CanActivate`) según el Rol.
* **Interceptores:** Inyección automática del Token en cada petición HTTP.
* **Visualización:** Dashboards con gráficos dinámicos y tablas interactivas.

---

## 7. Documentación de API (OpenAPI)

La API está documentada bajo el estándar OpenAPI 3.0 (Swagger).

* **URL de Documentación:** [Swagger UI - Net_Academy](https://backendproyecto-production-1c31.up.railway.app/swagger-ui/index.html)
* **Funcionalidad:** Permite probar los endpoints (`GET`, `POST`, `PUT`, `DELETE`) en tiempo real contra la base de datos de producción.

---

## 8. Video de Presentación Funcional

A continuación, se presenta la demostración del flujo completo del sistema (Reserva, Notificación y Reportes):

[![Ver Video en YouTube](https://img.youtube.com/vi/k_3y3pPgyDU/hqdefault.jpg)](https://www.youtube.com/watch?v=k_3y3pPgyDU)

*(Clic en la imagen para ver el video)*

---
*© 2024 Net_Academy Development Team*
