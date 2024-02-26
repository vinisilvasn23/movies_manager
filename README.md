# Projeto de Gestão de Usuários e Filmes

Este é um projeto para gestão de usuários e filmes, desenvolvido utilizando Spring Boot.

## Funcionalidades

O projeto oferece as seguintes funcionalidades:

### Usuários

- Criar um novo usuário
- Listar todos os usuários
- Recuperar um usuário pelo ID
- Atualizar parcialmente um usuário
- Excluir um usuário

### Filmes

- Criar um novo filme
- Listar todos os filmes
- Recuperar um filme pelo ID
- Atualizar parcialmente um filme
- Excluir um filme

### Autenticação

- Efetuar login

## Pré-requisitos

- JDK 11 ou superior
- Maven

## Executando o Projeto

Para executar o projeto localmente, siga estas etapas:

1. **Clone o repositório:**

   ```shell
   git clone https://github.com/vinisilvasn23/movies_manager

2. **Configure o ambiente virtual:**

Ajuste as configurações do banco de dados no arquivo env.example
```shell
renomeie en.example para .env
```
3. **Execute o projeto usando o Maven:**

```shell
cd movies_manager
mvn install clean
mvn spring-boot:run
```

**Endpoints da API**

Usuários:
```shell
POST /users: Cria um novo usuário
GET /users: Lista todos os usuários
GET /users/{id}: Recupera um usuário pelo ID
PATCH /users/{id}: Atualiza parcialmente um usuário
DELETE /users/{id}: Exclui um usuário
```

Filmes:

```shell
POST /movies: Cria um novo filme
GET /movies: Lista todos os filmes
GET /movies/{id}: Recupera um filme pelo ID
PATCH /movies/{id}: Atualiza parcialmente um filme
DELETE /movies/{id}: Exclui um filme
```

Autenticação:

```shell
POST /auth/login: Efetua login
```

**Tecnologias Utilizadas**

-Java
-Spring Boot
-Spring Data JPA
-Spring Security
-Maven
-Pstgresql
-Mapstruct
-ModelMapper