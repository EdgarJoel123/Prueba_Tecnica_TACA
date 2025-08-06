# Prueba_Tecnica_TACA
Evaluación técnica para el trabajo de desarrollador
# Microservicio: persona-cliente-ms

Este proyecto es parte de una solución de microservicios desarrollada como parte de una prueba técnica. El microservicio `persona-cliente-ms-services` gestiona el backend relacionado a entidades de persona y cliente, conectándose a una base de datos PostgreSQL.

## 🧰 Tecnologías utilizadas

- Java 11
- Spring Boot 2.7.18
- Gradle 8.x
- Spring Data JPA
- PostgreSQL
- Spring Security (configuración por defecto)
- HikariCP
- Swagger / Springdoc
- Arquitectura en módulos multi-proyecto Gradle

## 📦 Estructura del proyecto

```bash
persona-cliente-ms/
├── persona-cliente-ms-client
├── persona-cliente-ms-core
├── persona-cliente-ms-services     <-- Este es el microservicio principal
├── persona-cliente-ms-vo
├── build.gradle (raíz)
└── settings.gradle

🧪 Base de datos
Motor: PostgreSQL

Nombre: prueba_tecnica

Usuario: postgres

Contraseña: 141124

Puerto: 5432

Puedes ver esta base montada en tu entorno local dentro de DataGrip o cualquier cliente PostgreSQL.

⚙️ Configuración de Spring Boot
El archivo application.yaml se encuentra en:
persona-cliente-ms-services/src/main/resources/application.yaml
Fragmento relevante:
server:
  port: 8089
  servlet:
    context-path: /personCustomer

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/prueba_tecnica
    username: postgres
    password: 141124
▶️ Cómo ejecutar el microservicio
Puedes ejecutarlo desde IntelliJ IDEA o desde consola.

Opción 1: IntelliJ IDEA
Abre el proyecto completo.

Asegúrate de tener una Run/Debug Configuration para el módulo persona-cliente-ms-services.

Elige bootRun como tarea Gradle.

Haz clic en Run ▶️.

Opción 2: Línea de comandos
Desde la raíz del proyecto:
./gradlew :persona-cliente-ms-services:bootRun
🔐 Seguridad
Spring Security está habilitado por defecto.

Al iniciar por primera vez, se genera una contraseña aleatoria para el usuario user.

Ejemplo:

Using generated security password: c0f65300-9261-4239-bff1-b09857937efa
Accede en: http://localhost:8089/personCustomer/login

Opcion 3 con docker:
./gradlew :persona-cliente-ms-services:bootJar
docker-compose up --build
./gradlew :persona-cliente-ms-services:clean :persona-cliente-ms-services:build


📄 Documentación API
Si configuraste springdoc-openapi, puedes acceder a la documentación en:
http://localhost:8089/personCustomer/openapi

📌 Notas adicionales
Si ves el error "Main class name has not been configured" al ejecutar persona-cliente-ms-client, recuerda que ese módulo no tiene clase main, por lo tanto no se debe ejecutar con bootRun.

Para eliminar la pantalla de login puedes configurar una clase SecurityConfig para desactivar seguridad en desarrollo.

🧑‍💻 Autor
Proyecto desarrollado por: Edgar Joel Sanchez Lascano
Prueba técnica: Microservicio persona-cliente con Spring Boot y PostgreSQL