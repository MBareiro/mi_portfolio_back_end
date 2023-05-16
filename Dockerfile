FROM amazoncorretto:17-alpine-jdk 
maintainer mbdev
copy  target/mbdev-0.0.1-SNAPSHOT.jar mbdev-app.jar            
entrypoint ["java", "-jar", "mbdev-app.jar"]  


