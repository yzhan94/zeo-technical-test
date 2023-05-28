# ZEO TECHNOLOGY - prueba de propgramación

Esta es la implementación para una prueba de programación para el proceso de
selección de ZEO TECHNOLOGY.

Se trata de una aplicación sencilla de creación y listado de usuarios,
desarrolada con SpringBoot 3 en la parte Backend y Angular 16 como frontend.

## Lanzamiento

### Backend

Para lanzar el backend será necesario tener instalado Java17 en el equipo.
Usamos la tarea de maven del plugin spring-boot mediante el Wrapper de maven
incluido:

```bash
cd backend;
./mvnw spring-boot:run
```

El backend tiene una propiedad de configuración que nos permite funcionar con
almacenamiento de usuarios persistente o volatil. Para ello, disponemos de la
propiedad `persistence.mode` en el fichero
`src/main/resources/application.properties`, con los posibles valores
`PERSISTENT` o `VOLATILE`. También podemos establecer los valores de esta
propiedad en lanzamiento usando el parámetro `-Dspring-boot.run.arguments`:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--persistence.mode=VOLATILE"
```


### Frontend

Para lanzar el frontend tendremos que instalar NodeJS v18.16.0 y NPM v9.5.1.
Con ellos, podemos ejecutar simplemente los siguientes comandos en el
directorio del proyecto:

```bash
npm install;
npm start
```

Esto lanzará un servidor web local en el puerto 4200, permitiendonos acceder a
la app en http://localhost:4200

