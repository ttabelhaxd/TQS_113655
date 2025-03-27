# Lab7 - Testes Não-Funcionais (Performance)

Este guião prático tem como objetivo principal a **implementação de testes não-funcionais**, com foco em performance, acessibilidade e boas práticas em aplicações web. Utilizaremos ferramentas como **k6** para testes de carga e **Lighthouse** para auditorias de qualidade frontend.

---

## Objetivos deste guião

- **Planejar testes de performance** para verificar Service Level Objectives (SLOs).
- **Utilizar a ferramenta k6** para desenvolver testes de carga baseados em HTTP.
- **Avaliar atributos de qualidade essenciais** em uma aplicação web (com a ferramenta Lighthouse).

---

## Conceitos Importantes

### **Testes de Performance com k6**
- **k6** é uma ferramenta de código aberto para testes de carga, permitindo simular tráfego de usuários e medir métricas como tempo de resposta, taxa de falhas e consumo de recursos.
- **Métricas-chave**: `http_req_duration`, `http_req_failed`, `iterations`, `VUs (Virtual Users)`.
- **Service Level Objectives (SLOs)**: Definem limites aceitáveis para métricas de performance (ex.: 95% das requisições devem ter duração < 1.1s).

### **Auditoria Frontend com Lighthouse**
- **Lighthouse** é uma ferramenta automatizada para avaliar performance, acessibilidade, boas práticas e SEO em aplicações web.
- **Core Web Vitals**: Métricas como LCP (Largest Contentful Paint), FID (First Input Delay), e CLS (Cumulative Layout Shift) impactam diretamente a experiência do usuário.

---

## Exercícios

### 7.1 Configuração do k6
- **Clonar o repositório** `k6-oss-workshop` e executar a aplicação QuickPizza em containers Docker.
- **Instalar o k6** e executar um teste simples para avaliar métricas básicas:
  ```bash
  k6 run test.js
  ```
- **Analisar resultados**: Duração das requisições, taxa de falhas e número de iterações.

### 7.2 Testes de Carga e Monitoramento

- **Cenários de carga**:
    - Teste com estágios de ramp-up, carga constante e ramp-down (ex.: 20 VUs em 30s).
    - Definir thresholds para SLOs (ex.: p(95) < 1.1s, falhas < 1%).
    - Adicionar checks para validar status code 200 e tamanho do corpo da resposta.

- **Visualizar resultados**:
    -Via linha de comando ou dashboard integrado (K6_WEB_DASHBOARD=true).
    -Exportar métricas para Prometheus para análise detalhada.

### 7.3 Performance e Acessibilidade Frontend (Lighthouse)

- **Auditoria com Lighthouse**:
```bash
lighthouse --view http://127.0.0.1:3333
```

- **Análise de resultados**:
    - Identificar métricas críticas (ex.: LCP, CLS).
    - Sugerir melhorias para acessibilidade (ex.: contraste de cores, atributos alt em imagens).
    - Comparar resultados entre navegador (Chrome DevTools) e CLI, considerando dispositivos móveis vs. desktop.

### 7.4 Aplicação em Cenários Reais
- Auditar um site existente (ex.: https://www.ua.pt) com Lighthouse.
- Discutir impactos de problemas identificados e desafios para corrigi-los em sistemas já em produção.

### Recursos Úteis
- Documentação do k6: https://k6.io/docs/
- Core Web Vitals: https://web.dev/vitals/
- Lighthouse: https://developer.chrome.com/docs/lighthouse/