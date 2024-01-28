# Administrador de Tasas

Este proyecto se encarga facilitar el acceso a las cotizaciones de tasas de cambio, adminte solo BOB y USD
Se integra con el servicio de https://currencylayer.com/ para obtener el tipo de cambio
actualizado y lo almacena en una base de datos mongodb.

Cuando se consulta un tipo de cambio, el servicio verifica si actualmente tiene registrado un tipo de cambio para el día
en curso para las monedas especificadas y en caso de que ya tenga un registro retorna el registro de su base de datos,
en caso de que no exista, consulta al servicio de https://currencylayer.com/ (Free - 100 mes) para obtener el tipo de cambio actual.

**Nota**: Al ser proyecto de prueba, se espera que no se supere la capa gratuita

### Stack Tecnológico

* Java 11
* SpringBoot 2.7.7
* Maven 3.9.4
* Openapi: 3.0.1
* MongoDB 6.0.12

### Patron de diseño

Para el proceso de implementación se consideró los siguientes patrones de diseño:

1. **Delegate**: Para la implementación de apis REST generadas con openapi
2. **Facade**: Para a implementación de Repositorios de Datos

### Documentación

#### Documentación de Apis

Se implementó el proceso de documentación median openapi, el archivo de definición de las apis se encuentra en la
siguiente ruta: [Api Tasas](src/main/resources/docs/tasas.yaml)

#### Documentación Técnica

Se crearon algunos diagramas con Plan UML, las fuentes de los mismos se encuentran
en [Tasas Diagramas](src/main/resources/docs/plantUML), para visualizar los diagramas puede apoyarse en algun plugin
como PlanUML Integration de Intellij o simplemente cargarlo a su servidor
online https://www.plantuml.com/plantuml/duml/SyfFKj2rKt3CoKnELR1Io4ZDoSa70000

### Ejecutar el proyecto en local

Este proyecto tiene conexión con la base de datos mongodb, por lo que debe levantar el contendedor de mongo primero

* Ejecutar el siguiente comando para levantar el contendor y crear la base de datos:

    ````shell
    docker-compose up tasas-database
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio servicios

* Levantar el proyecto con maven

    ````shell
    mvn spring-boot:run -Dspring-boot.run.profiles=localdocker-compose up tasas-database
    ````
  **Nota**: Este comando debe ser ejecutado en el directorio tasas

* Probar el proyecto mediante swagger
    ````http request
    http://localhost:8003/swagger-ui/index.html
    ````
