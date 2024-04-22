# Usando a imagem do
FROM maven:3.9.5-amazoncorretto-17 as builder

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o fonte para o diretório de trabalho
COPY .. .

# Roda os comandos clean e install para limpar os targets e gera-los novamente
RUN mvn clean install

# Use a imagem oficial do OpenJDK 17 como a imagem base
FROM openjdk:17-jdk-slim

ENV DB_HOST url
ENV DB_PORT user
ENV DB_NAME password
ENV DB_USER user
ENV DB_PASSWORD password

ENV API_BILLING_URL url
ENV API_CLIENTS_URL url
ENV API_PRODUCTS_URL url

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o fonte já buildado para ser executado
COPY --from=builder /app .

# Exponha a porta em que a aplicação Spring Boot irá escutar (substitua pela porta correta)
EXPOSE 8012

# Comando para executar a aplicação Spring Boot
ENTRYPOINT [ "java", "-jar", "/app/adapter-rest/target/adapter-rest-1.0.0.jar", "--spring.data.mongodb.host=${DB_HOST}", "--spring.data.mongodb.port=${DB_PORT}", "--spring.data.mongodb.database=${DB_NAME}", "--spring.data.mongodb.username=${DB_USER}", "--spring.data.mongodb.password=${DB_PASSWORD}", "--api.billing.url=${API_BILLING_URL}", "--api.clients.url=${API_CLIENTS_URL}", "--api.products.url=${API_PRODUCTS_URL}" ]
