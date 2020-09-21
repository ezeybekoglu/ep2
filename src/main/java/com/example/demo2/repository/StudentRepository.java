package com.example.demo2.repository;

import com.example.demo2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByName(String name);
    Page<Student> findAll(Pageable pageable);
}
