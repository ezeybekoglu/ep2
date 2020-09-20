package com.example.demo2.controllers;

import com.example.demo2.repository.CourseRepository;
import com.example.demo2.repository.StudentRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;


    @DeleteMapping(value = "/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    @DeleteMapping(value = "/course/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }
}
