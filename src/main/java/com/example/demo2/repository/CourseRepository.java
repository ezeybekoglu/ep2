package com.example.demo2.repository;

import com.example.demo2.model.Course;
import com.example.demo2.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findByName(String name);
}
