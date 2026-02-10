# 🎓 Net_Academy v2.0: Sistema de Gestión de Portafolios

**Proyecto Integrador - Fase II**
**Asignatura:** Programación y Plataformas Web
**Estado:** Finalizado 🚀

---

## 🔗 Enlaces a Entregables (Código Fuente)

Cumpliendo con la arquitectura de microservicios, el sistema se divide en:

* **🖥️ FRONTEND (Angular + Firebase):** [Ver Repositorio](https://github.com/ArielCalleSteven/Proyecto-Formulario) | [🔗 Ver App en Vivo](https://portafolio-calle-torres-2025.web.app/login)
* **⚙️ BACKEND (Spring Boot + Railway):** [Ver Repositorio](https://github.com/ArielCalleSteven/Backend_Proyecto) | [🔗 Ver Swagger API](https://backendproyecto-production-1c31.up.railway.app/swagger-ui/index.html)

---

## 1. Descripción Técnica del Desarrollo (Integración Backend)

La plataforma **Net_Academy v2.0** migró de una arquitectura Serverless a una infraestructura **Monolítica Robusta** en Java.

### 🏗️ Arquitectura de Software
* **Backend:** Java 17, Spring Boot 3, Spring Security 6.
* **Base de Datos:** MySQL (Relacional) gestionada en Railway.
* **Seguridad:** Implementación de **JWT (JSON Web Tokens)** para autenticación *stateless* y encriptación de contraseñas con **BCrypt**.
* **Integración:** Comunicación vía API RESTful con el cliente Angular mediante interceptores HTTP.

### 🧩 Patrones y Tecnologías
* **Persistencia:** Uso de **Hibernate/JPA** para manejo de transacciones ACID y relaciones estrictas (1:N entre Usuarios y Proyectos).
* **Business Intelligence:** Generación de reportes PDF dinámicos (Blobs) desde el servidor.
* **Notificaciones:** Integración asíncrona con EmailJS para alertas de correo.

---

## 2. Documentación de Endpoints REST

La API cumple con el estándar **OpenAPI 3.0**. Toda la documentación es interactiva y permite probar los métodos `GET`, `POST`, `PUT`, `DELETE` en tiempo real.

* **URL de Acceso:** [Swagger UI - Net_Academy](https://backendproyecto-production-1c31.up.railway.app/swagger-ui/index.html)
* **Controladores Principales:**
    * `AuthController`: Login y Registro (Generación de Token).
    * `ProgrammerController`: Gestión de perfiles y filtrado.
    * `AdvisoryController`: Agendamiento y descarga de reportes PDF.

---

## 3. Guía de Usuario (Manual Funcional)

### 👨‍💼 ROL: ADMINISTRADOR
1.  **Ingreso:** Inicie sesión con credenciales de administrador. Accederá a la **"Consola de Sistema"** (Pantalla negra tipo terminal).
2.  **Filtrado:** Use las pestañas superiores `[FRONTEND]`, `[BACKEND]`, `[DEVOPS]` para filtrar programadores por especialidad.
3.  **Gestión:**
    * **Editar (✏️):** Modifique datos del programador.
    * **Horario (📅):** Asigne bloques de disponibilidad.
    * **Reporte PDF (📄):** Haga clic en el botón de descarga para generar el historial de asesorías del usuario.

### 👨‍💻 ROL: PROGRAMADOR
1.  **Perfil:** Al ingresar, verá su Dashboard personal con métricas ("Incoming Requests").
2.  **Solicitudes:** En la tabla inferior, revise las citas pendientes. Puede **Aceptar** o **Rechazar** la asesoría.
3.  **Edición:** Puede actualizar su biografía, foto y enlaces (GitHub/LinkedIn) desde el botón de configuración.

---

## 4. Video de Presentación Funcional

A continuación, se presenta la demostración completa del flujo: Login, Gestión de Usuarios, Reserva de Citas, Notificación por Correo y Descarga de Reportes.

[![Ver Video en YouTube](https://img.youtube.com/vi/k_3y3pPgyDU/hqdefault.jpg)](https://www.youtube.com/watch?v=k_3y3pPgyDU)

> **Nota:** El video demuestra la integración exitosa entre el Frontend (Firebase) y el Backend (Railway).

---

## 5. Información Institucional

| Categoría | Detalle |
| --- | --- |
| **Universidad** | Universidad Politécnica Salesiana - Sede Cuenca |
| **Carrera** | Computación / Ingeniería de Software |
| **Docente** | Ing. Cristian Timbi Sisalima |
| **Desarrolladores** | Ariel Calle & Juan Diego Torres |
| **Periodo** | Marzo 2024 – Agosto 2024 |

---
*© 2024 Net_Academy Development Team*
