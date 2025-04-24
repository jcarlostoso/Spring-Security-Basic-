# Spring Security Basic Config

Este proyecto es un ejemplo simple de una aplicación Spring Boot que implementa una configuración básica de **Spring Security**. Incluye autenticación con login por formulario y autenticación básica por HTTP, además de gestión de sesiones concurrentes.

## 🛠 Tecnologías utilizadas

- Java 17+
- Spring Boot 3+
- Spring Security

## 🔐 Características de Seguridad

- Configuración de seguridad con `SecurityFilterChain`.
- Autenticación vía formulario y HTTP Basic.
- Control de sesiones concurrentes.
- SessionRegistry para listar información de sesiones activas.
- Rutas públicas y protegidas.

## 🚀 Cómo iniciar el proyecto

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/spring-security-basic.git
   cd spring-security-basic
   ```

2. Asegúrate de tener Java 17+ y Maven instalados.

3. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

4. La aplicación estará disponible en:  
   `http://localhost:8080`

## 🔎 Endpoints disponibles

| Método | Ruta           | Seguridad      | Descripción                                     |
|--------|----------------|----------------|-------------------------------------------------|
| GET    | `/v1/index`    | Protegido      | Retorna mensaje solo si estás autenticado       |
| GET    | `/v1/index2`   | Público        | Retorna mensaje sin necesidad de autenticación  |
| GET    | `/v1/session`  | Protegido      | Retorna detalles de la sesión actual            |

## ⚙️ Configuración de seguridad

El archivo [`SecurityConfig.java`](src/main/java/com/example/springSecurity/config/SecurityConfig.java) contiene:

- Permisos por ruta (`/v1/index2` es pública, el resto requiere autenticación).
- Inicio de sesión con formulario (`formLogin`).
- Logout con redirección.
- Límite de 1 sesión activa por usuario (manejado por `SessionRegistryImpl`).

## 📦 Estructura del proyecto

```
spring-security-basic/
├── src/
│   ├── main/
│   │   ├── java/com/example/springSecurity/
│   │   │   ├── SpringSecurityApplication.java
│   │   │   ├── config/SecurityConfig.java
│   │   │   └── controller/CustomerController.java
│   └── resources/
│       └── application.properties
└── pom.xml
```


---

¡Si te resulta útil este proyecto, no olvides dejar una ⭐ en el repositorio!
