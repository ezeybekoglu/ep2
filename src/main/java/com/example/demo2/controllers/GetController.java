package com.example.demo2.controllers;
import com.example.demo2.NotFoundException;
import com.example.demo2.model.Course;
import com.example.demo2.model.CustomSTC;
import com.example.demo2.model.Student;
import com.example.demo2.model.StudentToCourse;
import com.example.demo2.repository.CourseRepository;
import com.example.demo2.repository.CustomSTCRepo;
import com.example.demo2.repository.STCRepository;
import com.example.demo2.repository.StudentRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetController extends Controller {


    @GetMapping("/student")
    List<Student> students() {
        return (List<Student>) studentRepository.findAll();
    }

    @GetMapping("/students/{page}")
    Page<Student> studentss(@PathVariable Integer page) {
        Pageable paging = PageRequest.of(page-1, 5);
        return (Page<Student>) studentRepository.findAll(paging);
    }
    // Single item

    @GetMapping("/student/{id}")
    Student one(@PathVariable Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }




    @GetMapping("/student/list")
    public List<Student> listStudent(Model model) {

        return (List<Student>) studentRepository.findAll();
    }

    @GetMapping("/course")
    public List<Course> listCourse(Model model) {

        return (List<Course>) courseRepository.findAll();
    }
    // Single item

    @GetMapping("/course/{id}")
    Course oneCourse(@PathVariable Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
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
