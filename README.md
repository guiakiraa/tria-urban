# TRIA

## Integrantes do Grupo

| Nome                      | RM      |
|---------------------------|---------|
| Alice Nunes dos Santos    | 559052  |
| Anne Rezendes Martins     | 556779  |
| Guilherme Akira Nakamatsu | 556128  |

## Link dos Vídeos

Pitch: https://youtu.be/H7F3I7Zxw-w?si=jwK8nPOAQlF8tWlr
Java: https://youtu.be/aLybToP_iDE?si=ptQTZColXNBUF48X
Cloud: https://youtu.be/-KKD-TsUsrg?si=46RTSXmNyFpr41jR

## Resumo da Solução — TRIA URBAN

As enchentes representam um dos desastres naturais mais recorrentes e devastadores no Brasil, especialmente em áreas urbanas com infraestrutura precária. Elas causam perda de vidas, danos materiais significativos e dificultam o acesso a serviços essenciais.

Com o objetivo de facilitar a comunicação entre pessoas afetadas por esses desastres e pontos de apoio, a aplicação TRIA URBAN oferece as seguintes funcionalidades:

### 1. Solicitar Ajuda
O usuário pode descrever seu problema e selecionar os tipos de produtos de que necessita. A solicitação é enviada automaticamente aos pontos de distribuição mais próximos, com base na localização.

### 2. Cadastrar Pontos de Distribuição
Usuários podem registrar um ponto de distribuição de itens, seja em uma residência (como a casa de um voluntário) ou em um estabelecimento comercial local. O objetivo é facilitar o acesso a doações em locais estratégicos e acessíveis à comunidade.

### 3. Encontrar Pontos Perto de Mim
Usuários que precisam de ajuda podem localizar os pontos de distribuição mais próximos com base em sua localização atual. O sistema destaca os locais que possuem os itens mais urgentes de que a pessoa necessita, garantindo rapidez e eficiência no atendimento.

---

## Instruções para Executar

### Pré-requisitos

- Java JDK 17+ instalado
- Git instalado

### Clonando o repositório

```bash
git clone https://github.com/guiakiraa/tria-urban.git
cd tria-urban
```

### Executando o projeto

Com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Agora acesse a aplicação em:

```
http://localhost:8080/swagger-ui/index.html#/
```

### Autenticação: Obtendo e Utilizando o Token

Para acessar os recursos protegidos da API, você precisará primeiro se autenticar. Siga os passos abaixo para obter seu token e usá-lo nas próximas requisições.

#### 1. Acessar o Endpoint de Login

Envie uma requisição **POST** para o seguinte endereço:

`localhost:8080/autenticacao/login`

---

#### 2. Configurar o Corpo da Requisição (Body)

No corpo da requisição (body), utilize as seguintes credenciais:

* **`username`**: `gui`
* **`senha`**: `senha123`

---

#### 3. Utilizar o Token nas Requisições

Após o login, será retornado um **token JWT**. Para acessar qualquer endpoint protegido da API, você deve incluir esse token no cabeçalho da requisição (**header**) da seguinte forma:

```
Authorization: Bearer <seu_token_aqui>
```

Substitua `<seu_token_aqui>` pelo token retornado na etapa de login.

## Devops Tools & Cloud Computing

Este repositório contém os seguintes arquivos importantes:

---

### 📄 `Dockerfile`

Arquivo utilizado para a criação da imagem Docker da aplicação. Ele define o ambiente necessário para a aplicação ser executada corretamente.

---

### 📄 `script.md`

Arquivo contendo os **corpos de requisição (body)** utilizados nos testes da API. Esse documento serve como referência para facilitar o reuso das requisições durante o desenvolvimento e testes.

---
