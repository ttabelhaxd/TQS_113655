# Lab 03: Testes de Integração com Spring Boot e MySQL

## Descrição
Este projeto tem como objetivo principal explorar **testes de integração** utilizando **Spring Boot, JUnit 5, Mockito e uma base de dados MySQL**.  
Através de diferentes tipos de testes, garantimos a **correta comunicação entre as camadas** da aplicação, incluindo a API e a base de dados, assegurando a robustez e confiabilidade do sistema.

---

## 🔹 Funcionalidades e Fluxo de Desenvolvimento
- Implementação de uma **API REST para gestão de carros** utilizando Spring Boot;
- Realização de **testes unitários** com a ajuda do **Mockito** para simular dependências;
- Execução de **testes de integração** com o **H2**, um banco de dados em memória;
- Adaptação dos testes para utilizar uma **base de dados real (MySQL)**, incluindo a configuração de um ambiente com **Docker Compose**;
- Configuração de **Docker Compose** para facilitar a criação e o gerenciamento do ambiente de testes.

---

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot**
- **JUnit 5**
- **Mockito**
- **Hamcrest**
- **Maven**
- **MySQL**
- **Docker Compose**

---

## 🚀 Executar os Testes
Para executar os testes, siga os passos abaixo:

1. Inicie o ambiente com **Docker Compose**:
   ```bash
   docker compose up -d
   ```
2. Verificar se o contentor está em execução com:
   ```bash
   docker compose ps -a
   ```
3. Executar os testes Maven:
    ```bash
    mvn clean test
    ```

4. Após executar os testes, limpar o ambiente com:
    ```bash
    docker compose down -v
    ```
