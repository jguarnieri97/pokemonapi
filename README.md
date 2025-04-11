# pokemonapi - demo
API de prueba para buscar pokemones integrados con PokeAPI (https://pokeapi.co/), crear entrenadores pokemon y asignar pokemones a los entrenadores.

## Tecnologías:

- Java 17
- Maven
- Spring-Boot 3.4
- JUnit 5
- Base de datos: H2 (in-memory)
- IDE: Intellij
- Postman

## Enlaces:

- Swagger: http://localhost:8080/swagger-ui/index.html
- Consola BD: http://localhost:8080/h2-console
  - url: jdbc:h2:mem:testdb
  - usuario: sa
  - password: (sin contraseña)

## Caso de uso:

Bajar el repositorio, abrir utilizar un IDE (de preferencia Intellij) compilar y ejecutar la aplicación. Dentro de la carpeta "resources" hay un archivo de colección para importar en postman, o se puede utilizar la interfaz de Swagger para realizar las peticiones a la API.

Una vez hecho eso, pasamos a probar los endpoint. Buscar un pokemon por su "nombre" para probar la integración. Luego se debe crear un Entrenador con su "nombre". Seguido a eso, se debe asignar un pokemon por su "nombre" a el id del Entrenador creado. Crear otro entrenador, obtenerlos todos, eliminar un entrenador con id y volver a obtener todos los entrenadores.

## Documentación importante:

- WebClient (para las integraciones con otras API): https://www.baeldung.com/spring-5-webclient
- Exception Handler: https://www.baeldung.com/exception-handling-for-rest-with-spring
- Spring-Data: https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
- ORM: https://www.baeldung.com/spring-data-rest-relationships
- H2: https://www.baeldung.com/spring-boot-h2-database
