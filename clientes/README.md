# Administrador de Clientes

Este proyecto permite de crear y consultar clientes, la información de clientes lo almacena en una base de datos
mongodb.

Cuando se crea un cliente, se publica un evento de creación de cliente mediante kafka, para que el microservicio de
cuentas pueda registrar el evento, por lo que se sugiere que antes de realizar las pruebas, se asegure de que el
microservicios de cuentas también está ejecutándose.

### Stack Tecnológico

* Java 11
* SpringBoot 2.7.7
* Maven 3.9.4
* Openapi: 3.0.1
* MongoDB 6.0.12
* Zookeeper 3.9.1
* Kafka 3.4.1

### Patron de diseño

Para el proceso de implementación se consideró los siguientes patrones de diseño:

1. **Delegate**: Para la implementación de apis REST generadas con openapi
2. **Facade**: Para a implementación de Repositorios de Datos

### Documentación

#### Documentación de Apis

Se implementó el proceso de documentación median openapi, el archivo de definición de las apis se encuentra en la
siguiente ruta: [Api Clientes](src/main/resources/docs/clientes.yaml)

#### Documentación Técnica

Se crearon algunos diagramas con Plan UML, las fuentes de los mismos se encuentran
en [Clientes Diagramas](src/main/resources/docs/plantUML), para visualizar los diagramas puede apoyarse en algun plugin
como PlanUML Integration de Intellij o simplemente cargarlo a su servidor
online https://www.plantuml.com/plantuml/duml/SyfFKj2rKt3CoKnELR1Io4ZDoSa70000

### Ejecutar el proyecto en local

Este proyecto tiene conexión con la base de datos mongodb y usa kafka para publicar eventos, por lo que debe levantar
los contendedores de mongo y kafka primero

* Ejecutar el siguiente comando para levantar el contendor y crear la base de datos:

    ````shell
    docker-compose up clientes-database
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio servicios

* Ejecutar el siguiente comando para levantar kafka:

    ````shell
    docker-compose up kafka
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio servicios

* Levantar el proyecto con maven

    ````shell
    mvn spring-boot:run -Dspring-boot.run.profiles=local
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio clientes

* Probar el proyecto mediante swagger
    ````http request
    http://localhost:8000/swagger-ui/index.html
    ````
