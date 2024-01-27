# Administrador de Cuentas

Este proyecto permite de crear y consultar información de la cuenta, la información de cuentas se lo almacena en una
base de datos postgres.

Cuando se crea una cuenta, se publica un evento de creación de cuenta mediante kafka, para que el microservicio de
transacciones pueda registrar el evento, por lo que se sugiere que antes de realizar las pruebas, se asegure de que el
microservicios de transacciones también está ejecutándose.

### Stack Tecnológico

* Java 11
* SpringBoot 2.7.7
* Maven 3.9.4
* Openapi: 3.0.1
* Postgres 12
* Zookeeper 3.9.1
* Kafka 3.4.1

### Patron de diseño

Para el proceso de implementación se consideró los siguientes patrones de diseño:

1. **Delegate**: Para la implementación de apis REST generadas con openapi
2. **Facade**: Para a implementación de Repositorios de Datos

### Documentación

#### Documentación de Apis

Se implementó el proceso de documentación median openapi, el archivo de definición de las apis se encuentra en la
siguiente ruta: [Api Cuentas](src/main/resources/docs/cuentas.yaml)

#### Documentación Técnica

Se crearon algunos diagramas con Plan UML, las fuentes de los mismos se encuentran
en [Cuentas Diagramas](src/main/resources/docs/plantUML), para visualizar los diagramas puede apoyarse en algún plugin
como PlanUML Integration de Intellij o simplemente cargarlo a su servidor
online https://www.plantuml.com/plantuml/duml/SyfFKj2rKt3CoKnELR1Io4ZDoSa70000

### Ejecutar el proyecto en local

Este proyecto tiene conexión con la base de datos postgres y usa kafka para publicar y escuchar eventos, por lo que debe
levantar los contendedores de mongo y kafka primero

* Ejecutar el siguiente comando para levantar el contendor y crear la base de datos:

    ````shell
    docker-compose up cuentas-database
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio padre

* Ejecutar el siguiente comando para levantar kafka (Solo si aun no fue levantado):

    ````shell
    docker-compose up kafka
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio padre

* Levantar el proyecto con maven

    ````shell
    mvn spring-boot:run -Dspring-boot.run.profiles=local
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio **cuentas**

* Probar el proyecto mediante swagger
    ````http request
    http://localhost:8001/swagger-ui/index.html
    ````
