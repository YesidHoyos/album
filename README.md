# album
Microservicio para la gestión de álbumes de la red social woloxgram

## Tabla de contenido
* [Tecnologías](#tecnologías)
* [Prerequisitos](#prerequisitos)
* [Instalación](#instalación)
* [Uso](#uso)

## Tecnologías
Proyecto creado con:
* Versión Java: 1.8
* Versión Spring-boot: 2.4.5
* Versión Maven: 3.6.1
* Eclipse sts

## Prerequisitos
* Versión Java 8 instalada
* Versión Maven: 3.6.1 or later instalada

## Instalación
Para correr este proyecto en local, ejecuta los siguientes comandos usando maven y java

```
$ cd /{workdir}/album
$ mvn clean install
$ cd target
$ java -jar album-0.0.1-SNAPSHOT.jar
```
Para correr este proyecto en docker, ejecuta los siguientes comandos:
```
$ cd /{workdir}/album
$ mvn clean install
$ (opcional, si la red aún no ha sido creada)docker network create --subnet 172.168.0.1/24 --gateway 172.168.0.2 -d bridge woloxgram-network
$ docker build -t album-microservice:1.0.0 .
$ docker run -d -p 9092:9092 --name AlbumMicroservice --network woloxgram-network --ip 172.168.0.23 album-microservice:1.0.0
```

## Uso
En el servicio album puede encontrar los siguientes endpoints
* **Obtener todos los albumes -** obtiene todos los albumes.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9092/albums```
* Si vas a consumirlo de producción  ```http://woloxgramalbum.us-east-2.elasticbeanstalk.com/albums```

* **Obtener album por usuario -** obtiene el album para el usuario dado.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9092/albums?userId={userId}```
* Si vas a consumirlo de producción  ```http://woloxgramalbum.us-east-2.elasticbeanstalk.com/albums?userId={userId}```

* **Obtener todos las fotoas -** obtiene todas las fotos.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9092/photos```
* Si vas a consumirlo de producción  ```http://woloxgramalbum.us-east-2.elasticbeanstalk.com/photos```

* **Obtener fotos por usuario -** obtiene las fotos del usuario dado.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9092/photos?userId={userId}```
* Si vas a consumirlo de producción  ```http://woloxgramalbum.us-east-2.elasticbeanstalk.com/photos?userId={userId}```

