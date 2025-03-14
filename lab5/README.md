# Lab5 - BDD com Cucumber Framework

Este guião prático tem como principal objetivo a **implementação de testes utilizando a metodologia BDD (Behavior-Driven Development)** com o **Cucumber Framework**. O foco é escrever cenários de teste descritivos e automatizá-los utilizando Cucumber para Java e JUnit.

---

## Objetivos deste guião

- **Escrever cenários de teste descritivos** - utilizando a linguagem Gherkin, que é legível tanto por programadores como por não programadores.
- **Automatizar cenários de teste** - utilizando o Cucumber Framework para Java e JUnit.
- **Integrar BDD com histórias de usuário** - para melhorar a rastreabilidade dos requisitos de negócio até o código.

---

## Conceitos Importantes utilizados neste Guião

### **BDD (Behavior-Driven Development)**
O **BDD** é uma metodologia que promove a colaboração entre desenvolvedores, testadores e stakeholders não técnicos. O Cucumber é uma ferramenta que suporta BDD, permitindo que cenários de teste sejam escritos em linguagem natural (Gherkin) e depois automatizados.

### **Gherkin DSL**
A linguagem **Gherkin** é utilizada para escrever cenários de teste de forma descritiva. Ela segue uma estrutura simples, com palavras-chave como `Given`, `When`, `Then`, `And`, e `But`, que descrevem o comportamento esperado do sistema.

### **Cucumber Expressions**
O **Cucumber** suporta duas formas de mapear os passos dos cenários para código Java: **expressões regulares** e **Cucumber Expressions**. A prática recomendada é utilizar **Cucumber Expressions**, que são mais simples e legíveis.

### **Integração com JUnit**
O Cucumber pode ser integrado com o **JUnit** para executar os cenários de teste. Isso permite que os testes sejam executados como parte de um pipeline de integração contínua.

## Exercícios

### 5.1 Introdução ao Cucumber (Calculadora RPN)
- Criar um projeto Java com as configurações adequadas para utilizar o Cucumber.
- Escrever um arquivo `.feature` para descrever o comportamento de uma calculadora RPN.
- Implementar os passos dos cenários de teste em Java.
- Executar os testes e verificar os resultados.

### 5.2 Passagem de Dados para Testes (Biblioteca)
- Escrever cenários de teste para uma classe `Library` que gerencia uma coleção de livros.
- Considerar diferentes cenários de busca, como busca por autor, titulo ou data de publicação.
- Utilizar **Cucumber Expressions** e **DataTables** para passar dados para os testes.

### 5.3 Automação Web (Biblioteca Online)
- Desenvolver cenários de teste para uma biblioteca online, utilizando o **Selenium WebDriver** em conjunto com o Cucumber.
- Implementar a automação dos testes utilizando Cucumber, JUnit e Selenium.

---

## Recursos Úteis
- Livro “Cucumber for Java” de Boni García.
- Documentação oficial do Cucumber: [https://cucumber.io/docs](https://cucumber.io/docs)

---

Este guião proporciona uma introdução prática à metodologia BDD e à automação de testes utilizando o Cucumber Framework, abordando desde a escrita de cenários descritivos até a integração com ferramentas como Selenium e JUnit.