# ForoHub - Challenge Alura Backend

ForoHub es una API REST desarrollada en Java con Spring Boot para gestionar un foro de discusiones. El sistema permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre tópicos, garantizandola seguridad mediante autenticación basada en tokens JWT y siguiendo las mejores prácticas de desarrollo.

## Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 3.3.0**
* **Spring Security** (Autenticación y Autorización)
* **JSON Web Token (JWT)** (Seguridad basada en Tokens)
* **Spring Data JPA** (Persistencia de datos)
* **MySQL** (Base de datos relacional)
* **Flyway** (Migraciones de base de datos)
* **Jakarta Validation** (Validaciones de Beans)
* **Maven** (Gestor de dependencias)

## Funcionalidades Principales

### 1. Gestión de Tópicos (CRUD)
* **Registro de tópicos:** Los usuarios pueden crear nuevos temas con título, mensaje, autor y curso.
* **Listado general:** Muestra los tópicos paginados (10 por página) y ordenados por fecha de creación ascendente.
* **Filtros de búsqueda:** Búsqueda avanzada por nombre de curso y año de creación.
* **Detalle de tópico:** Consulta de un tópico específico por su ID.
* **Actualización:** Permite editar el título, mensaje e información del curso de un tópico existente.
* **Eliminación:** Borrado físico de tópicos previa verificación de existencia por ID.

### 2. Seguridad y Reglas de Negocio
* **Autenticación JWT:** Solo usuarios registrados pueden interactuar con la API (excepto el login).
* **Validación de duplicados:** No se permite el registro de tópicos con el mismo título y mensaje exactos.
* **Integridad de datos:** Validaciones para asegurar que los campos obligatorios no sean nulos y que los IDs de autor/curso existan.

## Cómo Ejecutar el Proyecto

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/TU_USUARIO/ForoHub.git](https://github.com/TU_USUARIO/ForoHub.git)
    ```
2.  **Configurar la Base de Datos:**
    * Crea tu base de datos en MySQL.
    * Actualiza las credenciales en `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost/TU_BASE_DE_DATOS
        spring.datasource.username=TU_USUARIO
        spring.datasource.password=TU_CONTRASEÑA
        ```
3.  **Configurar Variables de Entorno:**
    * Define una variable llamada `JWT_SECRET` para la firma de los tokens.
4.  **Ejecutar la aplicación:**
    * Puedes usar el comando `mvn spring-boot:run` o ejecutar la clase `ForoHubApplication` desde tu IDE.

## Pruebas en Insomnia
Para interactuar con la API:
1.  Realizar una petición `POST` a `/login` con las credenciales de usuario para obtener el Token JWT.
2.  Copiar el token y configurarlo en la pestaña **Auth -> Bearer Token** de las demás peticiones.
3.  Utilizar los endpoints:
    * `GET /topicos`
    * `POST /topicos`
    * `PUT /topicos`
    * `DELETE /topicos/{id}`
  
## Vista Previa
-Autenticar:

<img width="1188" height="306" alt="image" src="https://github.com/user-attachments/assets/c25b81d9-1c2f-4610-9a69-fa29fb5b425a" />

-Registrar:

<img width="1188" height="349" alt="image" src="https://github.com/user-attachments/assets/f9cf0d77-4ddb-43f2-9f39-cbf0c998b159" />

-Listar:

<img width="1179" height="375" alt="image" src="https://github.com/user-attachments/assets/5befd22a-8548-40c7-9de0-bacd367900f0" />

-Actualizar:

<img width="1188" height="350" alt="image" src="https://github.com/user-attachments/assets/2e1ccb19-5982-44e7-bebc-035576dd78ae" />

-Eliminar:

<img width="601" height="283" alt="image" src="https://github.com/user-attachments/assets/46acfa00-e90a-4b36-a517-1992d3c27f7a" />

---
Desarrollado por Juan Arturo Camarillo Gutierrez - 2026 🎓
