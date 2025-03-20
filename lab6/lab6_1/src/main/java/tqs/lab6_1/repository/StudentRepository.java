package tqs.lab6_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.lab6_1.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);
    Student findByEmail(String email);
    Student findByStudentNumber(String studentNumber);
}
