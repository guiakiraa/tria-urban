# Etapa 1: build usando imagem do Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

# Cria diretório para a aplicação
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Compila o projeto e gera um JAR
RUN mvn clean package -DskipTests

# Etapa 2: runtime usando imagem leve do JDK
FROM eclipse-temurin:17-jre-alpine

# Cria usuário com diretório home e shell bash
RUN adduser -h /home/gs -s /bin/sh -D gs

# Diretório do app
WORKDIR /home/gs

# Copia o JAR gerado da etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Altera a permissão do JAR para o usuário
RUN chown gs:gs app.jar

# Alterna para o usuário não root
USER gs

# Exposição da porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]