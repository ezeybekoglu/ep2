package com.example.demo2.controllers;
import com.example.demo2.model.Course;
import com.example.demo2.model.CustomSTC;
import com.example.demo2.model.Student;
import com.example.demo2.model.StudentToCourse;
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
    private IStudentService studentService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISTCService stcService;
    @Autowired
    private ICSTCService castService;

    @GetMapping("/student/list")
    public List<Student> listStudent(Model model) {

        var students = (List<Student>) studentService.findAll();

        model.addAttribute("students", students);

        return students;
    }

    @GetMapping("/course/list")
    public List<Course> listCourse(Model model) {

        var courses = (List<Course>) courseService.findAll();

        model.addAttribute("courses", courses);

        return courses;
    }

    @GetMapping("/stc/list")
    public List<StudentToCourse> listSTC(Model model) {

        var stc = (List<StudentToCourse>) stcService.findAll();

        model.addAttribute("stc", stc);

        return stc;
    }
    @GetMapping("/cast/list")
    public List<CustomSTC> listCSTC(Model model) {

        var cast = (List<CustomSTC>) castService.findAll();

        model.addAttribute("cast", cast);

        return cast;
    }
}
