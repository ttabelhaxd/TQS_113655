# Lab4 - Testes Automatizados na Web com Selenium e Docker

Este guião prático tem como principal objetivo a **automação de testes da camada web**, utilizando o **Selenium WebDriver**, e, no último exercício, executando testes em navegadores dentro de **Docker Containers**.

---

## Objetivos deste guião

- **Escrever testes de UI (User Interface) automatizados** - com recurso ao Selenium WebDriver.
- **Utilizar o padrão Page Object** - para melhorar a organização e reutilização do código.
- **Executar testes em diferentes navegadores** - de forma isolada e controlada usando Docker.

---

## Conceitos Importantes utilizados neste Guião

### **Selenium WebDriver**
O **Selenium WebDriver** permite interagir com um navegador como se fosse um utilizador real. Podemos realizar, por exemplo, as seguintes ações:
- Abrir uma página
- Clicar em botões e links
- Preencher formulários
- Validar o conteúdo das páginas

### **Locators e Identificação de Elementos**
A escolha dos *locators* influencia bastante a estabilidade do teste. Neste laboratório, explorámos:
- `By.id()` e `By.cssSelector()` como estratégias mais estáveis.
- `By.xpath()` quando necessário, mas sempre tendo em atenção as dependências frágeis no HTML.

### **Page Object Pattern**
O **Page Object Pattern** é importante visto que melhora a organização dos testes ao separar a lógica de interação da lógica dos testes. Este padrão é caracterizado por:
- Reduzir a duplicação de código.
- Facilitar a manutenção e a escalabilidade dos testes.
- Tornar os testes mais legíveis e fáceis de modificar.

### **Utilização de Docker para Testes**
Em vez de instalar navegadores localmente, utilizámos **Docker** para executar os testes. Vantagens:
- Ter um ambiente isolado e controlado.
- Podemos testar diferentes versões do mesmo navegador.
- Evitar problemas de compatibilidade de versões.

---

## Exercícios

### 4.1 WebDriver “hello world”
- Implementar um exemplo básico de uso do Selenium WebDriver.
- Navegar para uma página e verificar o URL.

### 4.2 Gravação Interativa de Testes
- Utilizar o Selenium IDE para gravar e reproduzir testes.
- Exportar o teste para Java e integrá-lo num projeto JUnit 5.

### 4.3 Seleção de Elementos com Locators
- Implementar testes que utilizam diferentes estratégias de localização de elementos.
- Introduzir waits explícitos para aumentar a robustez dos testes.

### 4.4 Page Object Pattern
- Implementar o padrão Page Object para melhorar a legibilidade e manutenção dos testes.

### 4.5 Variações de Navegadores
- Executar testes em navegadores dentro de containers Docker.

---

## Recursos Úteis
- Livro “Hands-On Selenium WebDriver with Java” de B. Garcia.
- Críticas e alternativas ao Page Object Pattern para aplicações web modernas.

---

Este guião proporciona uma introdução prática à automação de testes web, abordando desde conceitos básicos até técnicas avançadas como a utilização de Docker para testes em diferentes navegadores.