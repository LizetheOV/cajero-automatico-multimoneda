# Administrador de Transacciones

Este proyecto permite realizar depósitos y retiros, la información de las transacciones realizadas se lo almacena en una
base de datos postgres.

Cuando se registra una transacción, se publica un evento de actualización de cuenta mediante kafka, para que el
microservicio de cuentas pueda escuchar el evento y actualizar el saldo de la cuenta, por lo que antes de
realizar las pruebas, debe asegurarse de que el microservicios de cuentas también está ejecutándose.

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
siguiente ruta: [Api Transacciones](src/main/resources/docs/transacciones.yaml)

#### Documentación Técnica

Se crearon algunos diagramas con Plan UML, las fuentes de los mismos se encuentran
en [Transacciones Diagramas](src/main/resources/docs/plantUML), para visualizar los diagramas puede apoyarse en algún plugin
como PlanUML Integration de Intellij o simplemente cargarlo a su servidor
online https://www.plantuml.com/plantuml/duml/SyfFKj2rKt3CoKnELR1Io4ZDoSa70000

### Ejecutar el proyecto en local

Este proyecto tiene conexión con la base de datos postgres y usa kafka para publicar y escuchar eventos, por lo que debe
levantar los contendedores de postgres y kafka primero

* Ejecutar el siguiente comando para levantar el contendor y crear la base de datos:

    ````shell
    docker-compose up transacciones-database
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio servicios

* Ejecutar el siguiente comando para levantar kafka (Solo si aun no fue levantado):

    ````shell
    docker-compose up kafka
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio servicios

* Levantar el proyecto con maven

    ````shell
    mvn spring-boot:run -Dspring-boot.run.profiles=local
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio **transacciones**

* Probar el proyecto mediante swagger
    ````http request
    http://localhost:8003/swagger-ui/index.html
    ````
