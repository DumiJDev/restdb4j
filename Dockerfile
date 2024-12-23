# Etapa 1: Build com GraalVM e Native Image
FROM ghcr.io/graalvm/native-image:latest AS builder

# Instalar ferramentas necessárias
RUN gu install native-image

# Configurar diretório de trabalho
WORKDIR /app

# Copiar o código-fonte e arquivos de configuração para o contêiner
COPY . .

# Construir o executável nativo
RUN ./mvnw -Pnative clean package

# Etapa 2: Criar imagem final e minimalista
FROM alpine:3.21

# Instalar dependências runtime para imagens nativas Spring Boot
RUN apk add --no-cache libc6-compat

WORKDIR /app

# Copiar o executável nativo gerado
COPY --from=builder /app/target/restdb restdb

# Expor a porta da aplicação
EXPOSE 8080

ENV RESTDB_NAME=""
ENV RESTDB_PLATFORM=""
ENV RESTDB_PORT=""
ENV RESTDB_USER=""
ENV RESTDB_PWD=""
ENV RESTDB_DB=""
ENV RESTDB_DRIVER_CLASS=""
ENV RESTDB_URL=""
ENV RESTDB_SERVER_NAME=""

EXPOSE 8080

# Copy the native executable into the containers
COPY --from=builder /build/target/restdb restdb
ENTRYPOINT ["./restdb"]