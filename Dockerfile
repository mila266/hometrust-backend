# ====== Etapa 1: compilar el jar con Maven ======
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# Primero el pom para aprovechar la cache de dependencias
COPY pom.xml .
RUN mvn -q -e -B dependency:go-offline
# Luego el codigo y el empaquetado (sin ejecutar tests en la imagen)
COPY src ./src
RUN mvn -q -B clean package -DskipTests

# ====== Etapa 2: imagen liviana solo con el runtime ======
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Activa el perfil de produccion (lee variables de entorno)
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
