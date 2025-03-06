**Nota:** Resposta referente à alínea 'c'. As alíneas anteriores estão implementadas no projeto `lab3_2_cars`, conforme foi pedido.

### 3.3 - Teste de Integração  
**Alínea c): Vantagens e Desvantagens de usar uma Base de Dados real**

#### Vantagens:

1. **Maior fidelidade:** Permite testar em um ambiente que simula o cenário real de produção.  
2. **Verificação de conexões e queries SQL:** Identifica possíveis problemas antes da implementação em produção.  
3. **Detecção de problemas de concorrência:** Avalia como o sistema lida com múltiplas transações simultâneas.  
4. **Redução de imprevistos em produção:** Simula condições realistas, minimizando surpresas no ambiente final.  

#### Desvantagens:

1. **Testes mais lentos:** Em comparação com bases de dados em memória, como o H2, o MySQL pode ser mais lento.  
2. **Maior complexidade:** Exige configurações adicionais, como uso de Docker, gestão de permissões, etc.  
3. **Risco de persistência de dados:** Se a base de dados não for corretamente limpo entre testes, pode haver interferência nos resultados.  
4. **Dependência externa:** Necessidade de garantir que a base de dados esteja sempre disponível para execução dos testes.
  
