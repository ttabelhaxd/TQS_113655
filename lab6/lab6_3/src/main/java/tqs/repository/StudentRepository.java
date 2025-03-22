package tqs.repository;


import tqs.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findById(long id);
    Student save(Student student);
}
