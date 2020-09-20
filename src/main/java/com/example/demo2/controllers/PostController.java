package com.example.demo2.controllers;

import com.example.demo2.model.*;
import com.example.demo2.repository.CourseRepository;
import com.example.demo2.repository.CustomSTCRepo;
import com.example.demo2.repository.STCRepository;
import com.example.demo2.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PostController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private STCRepository stcRepository;
    @Autowired
    private CustomSTCRepo castRepo;




    @PostMapping("/student")
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }







    @PostMapping("/course")
    Course newCourse(@RequestBody Course newCourse) {
        return courseRepository.save(newCourse);
    }





    @RequestMapping(value = "/stc/add", method = RequestMethod.POST)
    public void addSTC(@RequestParam Map<String, String> requestParams) {
        Long studentID = Long.parseLong(requestParams.get("studentID"));
        Long courseID = Long.parseLong(requestParams.get("courseID"));

        Student student = studentRepository.findById(studentID).orElse(null);
        Course course = courseRepository.findById(courseID).orElse(null);
        if (course != null && student != null) {
            StudentToCourse stc = new StudentToCourse();
            stc.setCourseId(courseID);
            stc.setStudentId(studentID);
            stcRepository.save(stc);
        }
    }

    @RequestMapping(value = "/stc/update", method = RequestMethod.POST)
    public void updateSTC(@RequestParam Map<String, String> requestParams) {
        if ((!requestParams.containsKey("id") || !requestParams.containsKey("studentID")
                || !requestParams.containsKey("courseID"))
                || (requestParams.get("id").isEmpty()
                || requestParams.get("studentID").isEmpty()
                || requestParams.get("courseID").isEmpty())
        ) {
            //parameters empty or not defined
            throw new IllegalArgumentException("{\"error\":\"At least one parameter is invalid or not supplied\"}");
        }
        Long stcId = Long.parseLong(requestParams.get("id"));
        Long studentID = Long.parseLong(requestParams.get("studentID"));
        Long courseID = Long.parseLong(requestParams.get("courseID"));

        Student student = studentRepository.findById(studentID).orElse(null);
        Course course = courseRepository.findById(courseID).orElse(null);
        if (course != null && student != null) {
            StudentToCourse stc = new StudentToCourse();
            stc.setId(stcId);
            stc.setCourseId(courseID);
            stc.setStudentId(studentID);
            stcRepository.save(stc);
        }
    }

    @RequestMapping(value = "/stc/delete", method = RequestMethod.POST)
    public void stcCourse(@RequestParam Map<String, String> requestParams) {
        Long stcId = Long.parseLong(requestParams.get("id"));
        StudentToCourse stcToDelete = stcRepository.findById(stcId).orElse(null);
        if (stcToDelete != null) {
            //if id exists
            stcRepository.delete(stcToDelete);
        }
    }

    @RequestMapping(value = "/cast/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomSTC addCAST(@RequestParam(value = "studentName", required = true) String studentName, @RequestParam(value = "courseName", required = true) String courseName) {
        if (studentName.trim().isEmpty() || courseName.trim().isEmpty()) {
            //parameters empty or not defined
            throw new IllegalArgumentException("At least one parameter is invalid or not supplied");
        }
        Student student, s;
        Course course, c;
        CustomSTC cast;
        student = studentRepository.findByName(studentName.trim());
        course = courseRepository.findByName(courseName.trim());

        if (student == null) {
            s = new Student();
            s.setName(studentName);
            student = s;
        }
        if (course == null) {
            c = new Course();
            c.setName(courseName);
            course = c;
        }
        cast = new CustomSTC();
        if (castRepo.existsByCourse_idAndStudent_id(course.getId(), student.getId())) {
            System.out.println("exists");
            throw new IllegalArgumentException("Record exists.");
        } else {
            System.out.println("not exists");
            cast.setStudent(student);
            cast.setCourse(course);
            castRepo.save(cast);
        }
        return cast;
    }
    @PostMapping("/cast")
    CustomSTC newCourse(@RequestBody String str) throws JsonProcessingException {

        CustomSTC cast;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(str);
        Course course = mapper.convertValue(node.get("course"), Course.class);
        Student student = mapper.convertValue(node.get("student"), Student.class);


        //curl -X POST localhost:8080/cast -H 'Content-type:application/json' -d '{"course":{"id":3,"name":"mat2"},"student":{"id":2,"name":"efe1"}}'

        cast = new CustomSTC();
        if (castRepo.existsByCourse_idAndStudent_id(course.getId(), student.getId())) {
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
            castRepo.save(cast);
        }
        return cast;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final Err exceptionHandlerIllegalArgumentException(final IllegalArgumentException ex) {
        Err err = new Err();
        err.setError(ex.getMessage());
        return err;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Err handleMissingParams(MissingServletRequestParameterException ex) {
        Err err = new Err();
        err.setError(ex.getMessage());
        return err;
        // Actual exception handling
    }
}