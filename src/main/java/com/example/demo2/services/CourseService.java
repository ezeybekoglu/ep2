package com.example.demo2.services;

import com.example.demo2.model.Course;
import com.example.demo2.model.Student;
import com.example.demo2.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public List<Course> findAll() {

        var courses = (List<Course>) repository.findAll();

        return courses;
    }
    public Course findByName(String name) {

        Course course = (Course) repository.findByName(name);

        return course;
    }
}
