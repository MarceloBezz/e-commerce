# 🛒 E-Commerce API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen)

---

## 💻 Sobre o projeto
O projeto consiste na API de um e-commerce fictício feita em Java. A API engloba as funcionalidades, segurança e regras de negócio da aplicação.

Na aplicação, o usuário consegue autorização para acessar as rotas por meio da validação do token JWT fornecido ao realizar login. Porém, algumas rotas são restritas à determinadas **roles**.

O usuário logado consegue cadastrar e comprar produtos, adicioná-los em seu carrinho, visualizar suas compras e efetuar várias outras ações.

A API também utiliza validadores para reforçar as regras de negócio, impedindo operações indevidas nos serviços.

---

## 🛠 Stack utilizada
As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:
* `Java` v.21.0.2
* `Spring Boot` v.3.5.4
* `Spring Security`
* `Flyway`
* `MySQL`
* `Lombok`

---

## ⚙️ Endpoints
A API expõe os seguintes *endpoints* a partir da *base URL* `localhost:8080`:

* `POST /login` Realizar login

`/usuario`
* `GET` Listar todos os usuários
* `GET /{idUsuario}` Obter um usuário pelo id
* `POST /cadastro` Realizar cadastro
* `DELETE /{idUsuario}` Desativar usuário (exclusão lógica)
* `PATCH /{idUsuario}` Reativar usuário

`/produtos`
* `GET /{idProduto}` Obter um produto pelo ID
* `GET /categoria` Filtrar produtos por categoria
* `POST` Cadastrar um novo produto
* `PUT /{idProduto}` Editar um produto existente
* `DELETE /{idProduto}` Deletar um produto

`/carrinho`
* `GET` Recupera todos os produtos do carrinho do usuário logado
* `POST /inserir` Adiciona um produto ao carrinho do usuário logado
* `POST /finalizar` Finaliza a compra com os produtos do carrinho
* `DELETE /remover/{idProduto}` Remove um produto específico do carrinho
* `DELETE /limpar` Remove todos os produtos do carrinho do usuário logado

`/compra`
* `GET visualizar/todas/{idUsuario}` Visualizar todas as compras já feitas por um usuário
* `GET visualizar/{idCompra}` Visualizar uma compra pelo seu ID
* `POST /unico/{idProduto}` Realizar a compra de um único produto
* `POST /carrinho` Realizar a compra de produtos a partir de um carrinho

---

## ▶️ Como rodar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/MarceloBezz/e-commerce.git
```

2. Configure o `application.properties` com seus dados para acessar o **MySQL**

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

---
