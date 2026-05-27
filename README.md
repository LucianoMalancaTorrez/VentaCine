# Sistema de Ventas de Cine (VentaCine)

Este es un proyecto web desarrollado con **Spring Boot** para gestionar las operaciones de un cine. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre diferentes entidades del dominio como Clientes, Salas, Funciones, Películas y Ventas.

## Tecnologías Utilizadas

- **Java 17+** (o compatible)
- **Spring Boot** (Web, Data JPA)
- **Thymeleaf** como motor de plantillas para las vistas HTML.
- **Maven** para la gestión de dependencias y construcción del proyecto.

## Estructura del Proyecto

La arquitectura del proyecto sigue el patrón **MVC (Modelo-Vista-Controlador)**:

- `com.mycompany.ventacine.model`: Entidades de dominio (Cliente, ClienteVIP, Pelicula, Venta, etc.).
- `com.mycompany.ventacine.repository`: Interfaces para el acceso a la base de datos (Data JPA).
- `com.mycompany.ventacine.service`: Lógica de negocio de la aplicación.
- `com.mycompany.ventacine.controller`: Controladores que manejan las peticiones HTTP y renderizan las vistas.
- `src/main/resources/templates`: Vistas Thymeleaf (`.html`) para las pantallas del usuario.

## Cómo ejecutar la aplicación

1. Asegúrate de tener **Java** y **Maven** instalados en tu sistema.
2. Abre una terminal y sitúate en la raíz del proyecto.
3. Compila y ejecuta el proyecto con el siguiente comando:
   ```bash
   mvn spring-boot:run
   ```
4. Abre tu navegador y accede a `http://localhost:8080/` para utilizar el sistema.

## Documentación (Javadoc)

Para generar la documentación Javadoc del proyecto, ejecuta en la terminal:
```bash
mvn javadoc:javadoc
```
Los archivos HTML generados se encontrarán en el directorio `target/site/apidocs/`. Para ver la documentación, simplemente abre el archivo `index.html` en tu navegador.
