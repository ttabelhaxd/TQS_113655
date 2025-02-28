a) Examples that use AssertJ expressive methods chaining:

- No ficheiro A_EmployeeRepositoryTest.java:  
  assertThat(found).isNotNull().extracting(Employee::getName).isEqualTo(persistedAlex.getName());

- E no Ficheiro B_EmployeeService_UnitTest.java:  
  assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());

b) Transitive annotations included in @DataJpaTest:

@Transactional
@AutoConfigureTestDatabase
@AutoConfigureDataJpa
@BootstrapWith(DataJpaTestContextBootstrapper.class)

c) Example in which you mock the behavior of the repository (and avoid involving a database):

```java
@Mock
private EmployeeRepository employeeRepository;

@InjectMocks
private EmployeeServiceImpl employeeService;

@BeforeEach
public void setUp() {
    Employee john = new Employee("john", "john@deti.com");
    john.setId(111L);

    Employee bob = new Employee("bob", "bob@deti.com");
    Employee alex = new Employee("alex", "alex@deti.com");

    List<Employee> allEmployees = Arrays.asList(john, bob, alex);

    Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
    Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
    Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
    Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
    Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
}
```

d) What is the difference between standard @Mock and @MockBean?

O @Mock é utilizado para criar e injetar instâncias de mocks em testes unitários, geralmente com o Mockito.

e) What is the role of the file “application-integrationtest.properties”? In which conditions will it
be used?

O ficheiro application-integrationtest.properties é utilizado para definir propriedades específicas para os testes de integração. É utilizado quando a anotação @TestPropertySource é aplicada a uma classe de teste para carregar estas propriedades, normalmente para configurar uma base de dados real ou outras definições específicas do ambiente para testes de integração.

f) the sample project demonstrates three test strategies to assess an API (C, D and E) developed
with SpringBoot. Which are the main/key differences?

- C_EmployeeController_WithMockServiceTest.java:  
  Utiliza @WebMvcTest para carregar um ambiente web simplificado.
  Simula a camada de serviço utilizando @MockBean.
  Foca-se em testar a camada do controlador separadamente.
- D_EmployeeRestControllerIT.java:  
  @SpringBootTest com @AutoConfigureMockMvc para carregar o contexto da aplicação.
  Testa os endpoints da API com uma base de dados real (ou uma base de dados em memória, se configurada).
  Foca-se em testes de integração ponta a ponta da API.
- E_EmployeeRestControllerTemplateIT.java:
  Utiliza @SpringBootTest com @AutoConfigureTestDatabase e TestRestTemplate.
  Testa os endpoints da API com uma base de dados real (ou uma base de dados em memória, se configurada).
  Concentra-se em testes de integração ponta a ponta da API utilizando um cliente REST.
