package com.example.demo2.repository;

import com.example.demo2.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByName(String name);
}
