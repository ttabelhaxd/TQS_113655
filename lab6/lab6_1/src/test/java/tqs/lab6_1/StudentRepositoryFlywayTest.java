package tqs.lab6_1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tqs.lab6_1.entity.Student;
import tqs.lab6_1.repository.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepositoryFlywayTest {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    void shouldSaveStudent() {
        Student student = new Student("John Doe", "john.doe@example.com");
        studentRepository.save(student);

        Optional<Student> savedStudent = studentRepository.findById(student.getId());

        assertTrue(savedStudent.isPresent());
        assertEquals("John Doe", savedStudent.get().getName());
        assertEquals("john.doe@example.com", savedStudent.get().getEmail());
    }

    @Test
    @Order(2)
    void shouldFindStudentById() {
        Student student = new Student("Jane Doe", "jane.doe@example.com");
        studentRepository.save(student);

        Optional<Student> foundStudent = studentRepository.findById(student.getId());

        assertTrue(foundStudent.isPresent());
        assertEquals("Jane Doe", foundStudent.get().getName());
        assertEquals("jane.doe@example.com", foundStudent.get().getEmail());
    }

    @Test
    @Order(3)
    void shouldUpdateStudent() {
        Student student = new Student("Alice Smith", "alice.smith@example.com");
        studentRepository.save(student);

        student.setEmail("alice.smith@newdomain.com");
        studentRepository.save(student);

        Optional<Student> updatedStudent = studentRepository.findById(student.getId());

        assertTrue(updatedStudent.isPresent());
        assertEquals("Alice Smith", updatedStudent.get().getName());
        assertEquals("alice.smith@newdomain.com", updatedStudent.get().getEmail());
    }

    @Test
    @Order(4)
    void shouldDeleteStudent() {
        Student student = new Student("Bob Brown", "bob.brown@example.com");
        studentRepository.save(student);

        studentRepository.delete(student);

        Optional<Student> deletedStudent = studentRepository.findById(student.getId());

        assertFalse(deletedStudent.isPresent());
    }
}