package com.example.demo2.controllers;

import com.example.demo2.model.Course;
import com.example.demo2.model.Student;
import com.example.demo2.repository.CourseRepository;
import com.example.demo2.repository.CustomSTCRepo;
import com.example.demo2.repository.STCRepository;
import com.example.demo2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PutController {


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private STCRepository stcRepository;
    @Autowired
    private CustomSTCRepo castRepo;

    @PutMapping("/student/{id}")
    Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {

        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return studentRepository.save(newStudent);
                });
    }
    @PutMapping("/course/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {

        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return courseRepository.save(newCourse);
                });
    }
}
