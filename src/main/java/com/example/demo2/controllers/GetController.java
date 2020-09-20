package com.example.demo2.controllers;
import com.example.demo2.StudentNotFoundException;
import com.example.demo2.model.Course;
import com.example.demo2.model.CustomSTC;
import com.example.demo2.model.Student;
import com.example.demo2.model.StudentToCourse;
import com.example.demo2.repository.CourseRepository;
import com.example.demo2.repository.CustomSTCRepo;
import com.example.demo2.repository.STCRepository;
import com.example.demo2.repository.StudentRepository;
import com.example.demo2.services.ICSTCService;
import com.example.demo2.services.ICourseService;
import com.example.demo2.services.ISTCService;
import com.example.demo2.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private STCRepository stcRepository;
    @Autowired
    private CustomSTCRepo customSTCRepo;

    @GetMapping("/student")
    List<Student> students() {
        return (List<Student>) studentRepository.findAll();
    }
    // Single item

    @GetMapping("/student/{id}")
    Student one(@PathVariable Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }




    @GetMapping("/student/list")
    public List<Student> listStudent(Model model) {

        return (List<Student>) studentRepository.findAll();
    }

    @GetMapping("/course")
    public List<Course> listCourse(Model model) {

        return (List<Course>) courseRepository.findAll();
    }

    @GetMapping("/stc/list")
    public List<StudentToCourse> listSTC(Model model) {

        return (List<StudentToCourse>) stcRepository.findAll();
    }
    @GetMapping("/cast/list")
    public List<CustomSTC> listCSTC(Model model) {

        return (List<CustomSTC>) customSTCRepo.findAll();
    }
}
