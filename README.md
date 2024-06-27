# API Transferências

## Este serviço tem como objetivo realizar validação e efetuar transferências bancárias entre clientes.

### Pré-requisitos

Antes de começar, será necessário instalar em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java 17](https://www.oracle.com/br/java/technologies/downloads/#java17), [Docker](https://www.docker.com/products/docker-desktop/).

### Executando o Back End

```bash
# Clone este repositório
$ git clone https://github.com/pinheirocarlos/desafio-itau.git
$ cd desafio-itau


# Na pasta raiz do projeto, execute o seguinte comando:
$ docker-compose up --build -d

# O servidor iniciará na porta 8080 - acesse <http://localhost:8080>
```

### Documentação OpenAPI

É possível verificar o endpoint da aplicação, assim como os parâmetros necessário para execução.
Assim que a aplicação estiver rodando, consulte a documentação da API no Swagger UI
em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Validações em uma transferência

- API verifica se o ID do cliente existe através da chamada à API Cadastro, assim apenas clientes devidamente
  cadastrados podem realizar transferências;
- API verifica o ID da conta, saldo disponível, limite diário, assegurando que as transferências ocorram apenas quando
  houver recursos suficientes disponíveis;
- API realiza comunicação com a API do Bacen para notificar as transferências ocorridas com sucesso.

### Arquitetura de solução

Na raiz do projeto, na pasta "docs" constam os desenhos de solução elaborados, sendo um com o cenário atual da aplicação
e um segundo como uma proposta de melhoria.

### Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java 17](https://www.oracle.com/br/java/technologies/downloads/#java17)
- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/)

[![Linkedin Badge](https://img.shields.io/badge/-Carlos-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/carlos-pinheiro/)](https://www.linkedin.com/in/carlos-pinheiro/)