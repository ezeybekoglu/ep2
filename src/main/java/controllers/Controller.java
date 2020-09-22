package controllers;

import course.CourseRepository;
import studentToCourse.StudentToCourseRepository;
import student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class Controller {
    @Autowired
    public StudentRepository studentRepository;
    @Autowired
    public CourseRepository courseRepository;
    @Autowired
    public StudentToCourseRepository studentToCourseRepository;
}
