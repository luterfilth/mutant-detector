# Mutant Detector


Verificación de mutantes para derrotar X-Men


# Funciones


- Verificar el ADN para determinar si es mutante
- Consolidado de mutantes y humanos


## Tecnología


El proyecto esta desarrollado con las siguientes herramientas:
- [Spring Boot] - Framework de Java.
- [H2] - Base de datos en memoria (temporal).
- [App Engine - Google] - Configurado para poder desplegar en Google.
- [JUnit] - Pruebas unitarias y verificación de Código.
- [Maven] - Simplifica el proceso de compilado y configuración del proyecto.


## Instalación


Descargar el proyecto por medio de Github
```
git clone
```


Dirigirse al directorio del proyecto y compilar.
- Dentro de la carpeta del proyecto están los archivos mvnw, mvnw.bat *
```
mvnw clean package
```
Luego debes ejecutar el JAR, por defecto se ejecuta en el puerto 8080
```
cd target/
java -jar mutant-detector-0.0.1-SNAPSHOT.jar
```


Si deseas desplegar en Google debes tener una proyecto con App Engine y ejecutas el siguiente comando
```
clean package appengine:deploy
```


# Ejecución 


El sistema cuenta con el siguiente API Rest


- /mutation - POST - Método para verificar la cadena de ADN
- /stats - GET - Método consultar el estado de los ADN analizados


Para más información, puede descargar el siguiente archivo de Postman y verificar la estructurá.
https://www.getpostman.com/collections/eb0c0c10ad30803e47fa


# Referencias
- https://spring.io/projects/spring-boot
- https://junit.org/junit5/
- https://www.postman.com/
- https://www.h2database.com/html/main.html
- https://maven.apache.org/