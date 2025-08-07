# 🧾 API de Comandas - FURB

API REST desenvolvida para gerenciamento de comandas com autenticação via JWT. Projeto acadêmico da disciplina de Programação Web II — Ciência da Computação — FURB.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- H2 Database
- Swagger (OpenAPI 3)

---

## 🛠️ Requisitos

- Java 17+
- Maven

---

## ▶️ Como executar

```bash
git clone https://github.com/seu-usuario/seu-repo.git
cd seu-repo
./mvnw spring-boot:run
```

A API estará disponível em:  
`http://localhost:8080/RestAPIFurb`

---

## 🔐 Autenticação

Todos os endpoints (exceto `/login` e `/usuarios`) exigem um token JWT.

Após realizar o login, inclua o token no header das requisições:

```
Authorization: Bearer TOKEN_GERADO
```

---

## 📌 Endpoints

### ➕ Criar usuário

```http
POST /RestAPIFurb/usuarios
Content-Type: application/json
```

```json
{
  "nomeUsuario": "Luan Guarnieri",
  "emailUsuario": "luanxxxxx@email.com",
  "senhaUsuario": "luan123",
  "telefoneUsuario": "47900000000"
}
```

---

### 🔑 Login

```http
POST /RestAPIFurb/login
Content-Type: application/json
```

```json
{
  "email": "luanxxxxx@email.com",
  "senha": "luan123"
}
```

---

### 📋 Comandas

#### 🔍 Buscar todas

```http
GET /RestAPIFurb/comandas
Authorization: Bearer TOKEN_GERADO
```

---

#### 🔍 Buscar por ID

```http
GET /RestAPIFurb/comandas/{id}
Authorization: Bearer TOKEN_GERADO
```

---

#### ➕ Criar comanda

```http
POST /RestAPIFurb/comandas
Content-Type: application/json
Authorization: Bearer TOKEN_GERADO
```

```json
{
  "idUsuario": 1,
  "nomeUsuario": "Luan Guarnieri",
  "telefoneUsuario": "47900000000",
  "produtos": [
    {
      "nome": "Coca-Cola",
      "preco": 6.50
    },
    {
      "nome": "X-Burguer",
      "preco": 15.00
    }
  ]
}
```

---

#### ✏️ Atualizar comanda

```http
PUT /RestAPIFurb/comandas/{id}
Content-Type: application/json
Authorization: Bearer TOKEN_GERADO
```

---

#### ❌ Deletar comanda

```http
DELETE /RestAPIFurb/comandas/{id}
Authorization: Bearer TOKEN_GERADO
```

---

## 🧪 Swagger

Acesse a documentação interativa da API:

```
http://localhost:8080/RestAPIFurb/swagger-ui/index.html
```

---

## 🧠 Observações

- O projeto usa banco H2 em memória para testes.
- Token JWT tem validade de 30 minutos.
- As validações dos campos são aplicadas via anotações (`@NotBlank`, `@Email`, `@Size`, etc).

---

## 👨‍💻 Autor

Luan Guarnieri — Ciência da Computação — FURB  
Prof. Gabriel Vieira
