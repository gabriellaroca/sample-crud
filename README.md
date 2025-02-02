# Projeto Escola - Spring Boot

Este projeto é uma aplicação completa desenvolvida em **Spring Boot** para gerenciar escolas e alunos. Ele inclui cache, conexão com **PostgreSQL** e suporte para **deploy via Docker**.

## 📋 Requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- **Java 17**
- **Apache Maven**
- **Docker** e **Docker Compose**

## 🚀 Como Rodar o Projeto

### 1️⃣ Clonar o Repositório
```sh
  git clone git@github.com:gabriellaroca/sample-crud.git
  cd sample-crud
```

### 2️⃣ Rodar a Aplicação via Maven

Caso queira rodar sem Docker:
```sh
  mvn spring-boot:run
```

Ou, caso queira compilar antes:
```sh
  mvn clean install
```

### 3️⃣ Rodar a Aplicação via Docker

Caso prefira rodar a aplicação junto com o banco de dados utilizando **Docker Compose**, execute:
```sh
  docker-compose up
```
Isso iniciará tanto a aplicação quanto o banco PostgreSQL configurado.

## 📌 Configuração
A configuração do banco de dados está no arquivo `application.properties` ou `application.yml`. Caso precise ajustar a conexão, edite esses arquivos ou utilize variáveis de ambiente.

## 📡 Endpoints
Após iniciar o projeto, acesse a API em:
- `http://localhost:8080/api/v1/` (ajuste a porta conforme necessário)

- Documentação Swagger disponível em `http://localhost:8080/swagger-ui.html`

## 🔧 Tecnologias Utilizadas
- **Spring Boot** (Web, Security, Data JPA, Cache, Validation)
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**