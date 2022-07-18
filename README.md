# Doctors Network

Api para cadastro de usuários médicos, a qual se integra com o sistema SD Conecta.

## Execução

1. Clonar este repositório
```
git clone https://github.com/vitor-msp/doctors-network.git
```

2. Acessar a pasta baixada
```
cd doctors-network
```

3. Criar a imagem do projeto no Docker
```
sudo docker build -t doctors-network .
```

4. Executar o container no Docker

Obs.1: a porta 8080 da sua máquina deve estar liberada

```
sudo docker run -p 8080:8080 -d doctors-network
```

## Testes

1. A API tem rota padrão [/api/v1](http://localhost:8080/api/v1)

2. O banco de dados (H2) pode ser acessado [aqui](http://localhost:8080/h2)

3. Rotas:

  - POST: /api/v1/login - autenticação de usuário - body: LoginBody
    - para utilização dos endpoints autenticados, logar com e-mail "admin" e senha "admin", será retornado o Bearer token
  - POST: /api/v1/users - cadastro de usuário - body: UserBody - usar Bearer token
  - PUT: /api/v1/users/:id - edição de usuário pelo id - body: UserBody - usar Bearer token
  - DELETE: /api/v1/users/:id - remoção de usuário pelo id - usar Bearer token
  - GET: /api/v1/users/:id - consulta de usuário pelo id
  - GET: /api/v1/users?name=User\_Name&specialty=User\_Specialty - consulta de usuários com filtros opcionais de nome e especialidade do usuário
  - POST: /api/v1/broadcast = envia mensagem para todos os usuários - body: BroadcastMsg - usar Bearer token
4. Body:
  - LoginBody:
```
{
    "email": "user@email.com",
    "password": "test123"
}
```

  - UserBody:
```
{
    "user": {
        "email": "user@email.com",
        "password": "test123",
        "name": "user test",
        "surname": "last name",
        "mobilePhone": "1234"
    },
    "crms": [
        {
            "crm": "10",
            "uf": "MG",
            "specialty": "Pediatrician"
        },
        {
            "crm": "20",
            "uf": "SP"
        }
    ]
}
```

  - BroadcastMsg:
```
{
    "message": "mensagem a todos os usuários"
}
```
