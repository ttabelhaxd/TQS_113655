package tqs.repository;

import tqs.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ImplementStudentRepository implements StudentRepository {

    private final List<Student> students = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public Optional<Student> findById(long id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(idCounter.getAndIncrement());
        }
        students.add(student);
        return student;
    }

}
