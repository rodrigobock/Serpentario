# Estágio de compilação
FROM maven:3.8.6-eclipse-temurin-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio de execução
FROM tomcat:9.0-jdk8-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR gerado no estágio anterior
COPY --from=build /app/target/Serpentario.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

# Configura a porta dinamicamente para o Render e inicia o Tomcat
CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT:-8080}\\\"/\" /usr/local/tomcat/conf/server.xml && catalina.sh run"]
