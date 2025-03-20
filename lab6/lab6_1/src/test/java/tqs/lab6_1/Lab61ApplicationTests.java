package tqs.lab6_1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tqs.lab6_1.entity.Student;
import tqs.lab6_1.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@Import(TestcontainersConfiguration.class)
@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Lab61ApplicationTests {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer()
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private StudentRepository studentRepository;

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Test
    @Order(1)
    void testInsert() {
        Student student = new Student("John Doe", "email@gmail.com", "123456");
        studentRepository.save(student);
        Student found = studentRepository.findByName("John Doe");
        assertEquals(student, found);
    }

    @Test
    @Order(2)
    void testRetrieve() {
        Student found = studentRepository.findByName("John Doe");
        assertEquals("John Doe", found.getName());
    }

    @Test
    @Order(3)
    void testUpdate() {
        Student student = studentRepository.findByName("John Doe");
        student.setEmail("newemail@gmail.com");
        studentRepository.save(student);
        Student updated = studentRepository.findByName("John Doe");
        assertEquals("newemail@gmail.com", updated.getEmail());
    }

    @Test
    @Order(4)
    void testDelete() {
        Student student = studentRepository.findByName("John Doe");
        studentRepository.delete(student);
        Student notFound = studentRepository.findByName("John Doe");
        assertNull(notFound);
    }

}
