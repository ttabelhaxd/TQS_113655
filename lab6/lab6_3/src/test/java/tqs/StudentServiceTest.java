package tqs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tqs.entity.Student;
import tqs.repository.ImplementStudentRepository;
import tqs.repository.StudentRepository;
import tqs.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentRepository = new ImplementStudentRepository();
        studentService = new StudentService(studentRepository);
    }

    @Test
    void whenGetAllStudents_thenReturnStudentList() {
        studentService.saveStudent(new Student("John Doe", "johndoe@email.com"));
        studentService.saveStudent(new Student("Jane Smith", "janesmith@email.com"));

        List<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
    }

    @Test
    void whenGetStudentById_thenReturnStudent() {
        Student student = studentService.saveStudent(new Student("John Doe", "johndoe@email.com"));

        Optional<Student> foundStudent = studentService.getStudentById((student.getId()));

        assertTrue(foundStudent.isPresent());
        assertEquals("John Doe", foundStudent.get().getName());
    }

    @Test
    void whenSaveStudent_thenReturnSavedStudent() {
        Student student = studentService.saveStudent(new Student("John Doe", "johndoe@email.com"));

        assertNotNull(student.getId());
    }
}
