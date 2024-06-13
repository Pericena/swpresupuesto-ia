# Usa una imagen base de OpenJDK 17
FROM openjdk:17-jdk-alpine

# Añade el archivo JAR de tu aplicación al contenedor
ADD target/proyectossw2-springboot.jar app.jar

# Define el comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]