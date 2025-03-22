# Lab6 - Ferramentas Adicionais para Testes de Integração

Este guião prático tem como principal objetivo a **implementação de testes de integração utilizando ferramentas como Test Containers e REST Assured**. O foco é garantir que a aplicação seja totalmente funcional, utilizando Docker containers para simular dependências reais e testar APIs REST de forma eficiente.

---

## Objetivos deste guião

- **Aplicar o framework Test Containers** para usar instâncias gerenciadas de dependências conteinerizadas em testes de integração.
- **Utilizar REST Assured** como uma abordagem alternativa para testar APIs REST.
- **Escrever testes de integração** que garantam o funcionamento completo da aplicação, além dos testes unitários.

---

## Conceitos Importantes utilizados neste Guião

### **Test Containers**
O **Test Containers** é uma biblioteca de testes que fornece APIs leves e fáceis para inicializar testes de integração com serviços reais encapsulados em containers Docker. Com o Test Containers, você pode escrever testes que interagem com os mesmos tipos de serviços usados em produção, sem precisar de mocks ou serviços em memória.

### **REST Assured**
O **REST Assured** é uma biblioteca Java que facilita o teste de APIs REST. Ele permite escrever testes de integração de forma simples e legível, verificando status codes, corpos de resposta e outros aspectos das requisições HTTP.

### **Flyway Migrations**
O **Flyway** é uma ferramenta de migração de banco de dados que permite gerenciar mudanças no esquema da base de dados de forma controlada. Ele usa scripts SQL para aplicar alterações na base de dados, o que é útil para garantir que a base de dados esteja em um estado conhecido durante os testes.

---

## Exercícios

### 6.1 Test Containers no Spring Boot
- Criar um projeto Spring Boot simples para gerenciar uma entidade (por exemplo, Cliente, Funcionário, Estudante, etc.) que será persistida em um banco de dados PostgreSQL.
- Criar a entidade e o repositório.
- Desenvolver um teste de integração que prepare um banco de dados PostgreSQL, insira conteúdo no repositório e depois o recupere.
- Desenvolver diferentes métodos de teste (inserir, recuperar, atualizar, etc.) e garantir que sejam executados em uma ordem específica.

### 6.2 Migrações de Banco de Dados
- Continuar o projeto anterior e criar um script de inicialização do Flyway para criar o esquema do banco de dados e adicionar algumas entradas de exemplo.
- Criar uma nova classe de teste que utilize o Flyway para inicializar o banco de dados e execute os testes.

### 6.3 REST Assured
- Criar um projeto Java com classes de teste (sem Spring Boot) para testar o comportamento de uma API REST falsa (por exemplo, JSONPlaceholder).
- Escrever testes para verificar o status code, o conteúdo da resposta e o tempo de resposta da API.

### 6.4 Testes de Integração para o Projeto Cars (com REST Assured)
- Criar uma nova versão dos testes de integração para o projeto Cars (Spring Boot) utilizando REST Assured.
- Utilizar o suporte especial do REST Assured para trabalhar com Spring MockMvc, reduzindo o uso de recursos nos testes.
- Mockar a implementação do serviço necessário e usar REST Assured com MockMvc para executar os testes.

### 6.5 Projeto Cars com Teste de Integração Completo
- Criar uma versão diferente do teste de integração completo para o projeto Cars, utilizando REST Assured, Test Containers (para provisionar o PostgreSQL) e migrações do Flyway.
- Garantir que o teste seja executado em um ambiente web completo e que o banco de dados seja provisionado em um container de teste.

---

## Recursos Úteis
- Documentação oficial do Test Containers: [https://www.testcontainers.org/](https://www.testcontainers.org/)
- Documentação oficial do REST Assured: [https://rest-assured.io/](https://rest-assured.io/)
- Documentação oficial do Flyway: [https://flywaydb.org/](https://flywaydb.org/)

---

Este guião proporciona uma introdução prática ao uso de ferramentas avançadas para testes de integração, como Test Containers e REST Assured, permitindo que você escreva testes mais robustos e próximos do ambiente de produção.