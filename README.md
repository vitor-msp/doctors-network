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

1. A API tem rota padrão [/api/v1](http://localhost:8080/api/v1) e disponibiliza as rotas abaixo:

  - POST: /api/v1/users - cadastro de usuário
  - PUT: /api/v1/users/:id - edição de usuário pelo id
  - DELETE: /api/v1/users/:id - remoção de usuário pelo id
  - GET: /api/v1/users/:id - consulta de usuário pelo id
  - GET: /api/v1/users?name=User\_Name&specialty=User\_Specialty - consulta de usuários com filtros opcionais de nome e especialidade do usuário
  - POST: /api/v1/auth - autenticação de usuário
