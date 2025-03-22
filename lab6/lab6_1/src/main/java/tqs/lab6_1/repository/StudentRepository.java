package tqs.lab6_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tqs.lab6_1.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
