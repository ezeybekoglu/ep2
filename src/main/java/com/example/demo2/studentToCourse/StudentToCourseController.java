package com.example.demo2.studentToCourse;

import com.example.demo2.course.CourseRepository;
import com.example.demo2.student.Student;
import com.example.demo2.student.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo2.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class StudentToCourseController {
    @Autowired
    private StudentToCourseRepository studentToCourseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/cast")
    StudentToCourse newCourse(@RequestBody String str) throws JsonProcessingException {

        StudentToCourse cast;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(str);
        Course course = mapper.convertValue(node.get("com/example/demo2/course"), Course.class);
        Student student = mapper.convertValue(node.get("com/example/demo2/student/com.example.demo2.student"), Student.class);


        //curl -X POST localhost:8080/cast -H 'Content-type:application/json' -d '{"course":{"id":3,"name":"mat2"},"com.example.demo2.student":{"id":2,"name":"efe1"}}'

        cast = new StudentToCourse();
        if (studentToCourseRepository.existsByCourse_idAndStudent_id(course.getId(), student.getId())) {
            System.out.println("exists");
            throw new IllegalArgumentException("Record exists.");
        } else {
            System.out.println("not exists");

            Student s = new Student();
            s = studentRepository.findByName(student.getName());

            Course c = new Course();
            c = courseRepository.findByName(course.getName().trim());

            cast.setStudent(s);
            cast.setCourse(c);
            studentToCourseRepository.save(cast);
        }
        return cast;
    }

    @DeleteMapping(value = "/cast/{id}")
    public void deleteCast(@PathVariable Long id) {
        studentToCourseRepository.deleteById(id);
    }

    @GetMapping("/cast/list")
    public List<StudentToCourse> listCSTC(Model model) {

        return (List<StudentToCourse>) studentToCourseRepository.findAll();
    }


}
