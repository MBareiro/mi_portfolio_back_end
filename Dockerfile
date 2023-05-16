FROM amazoncorretto:17-alpine-jdk //de que imagen parte
maintainer mbdev //quien es el dueno
copy  target/mbdev-0.0.1-SNAPSHOT.jar mbdev-app.jar            //va a copiar el empaquetado github
entrypoint ["java", "-jar", "mbdev-app.jar"]  //es la instruccion q se ejecuta primero


