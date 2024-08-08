FROM eclipse-temurin:21-jdk AS build

# Define o diretório de trabalho
WORKDIR /app

# Copiar os arquivos base
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# build da aplicação
RUN ./mvnw clean package -DskipTests

# executação da aplicação
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
