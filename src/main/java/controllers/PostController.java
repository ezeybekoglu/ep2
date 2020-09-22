package controllers;

import com.example.demo2.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import student.Student;
import studentToCourse.StudentToCourse;

import java.util.Map;

@RestController
public class PostController extends Controller {


    @PostMapping("/student")
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }













    @RequestMapping(value = "/cast/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentToCourse addCAST(@RequestParam(value = "studentName", required = true) String studentName, @RequestParam(value = "courseName", required = true) String courseName) {
        if (studentName.trim().isEmpty() || courseName.trim().isEmpty()) {
            //parameters empty or not defined
            throw new IllegalArgumentException("At least one parameter is invalid or not supplied");
        }
        Student student, s;
        Course course, c;
        StudentToCourse cast;
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
        cast = new StudentToCourse();
        if (studentToCourseRepository.existsByCourse_idAndStudent_id(course.getId(), student.getId())) {
            System.out.println("exists");
            throw new IllegalArgumentException("Record exists.");
        } else {
            System.out.println("not exists");
            cast.setStudent(student);
            cast.setCourse(course);
            studentToCourseRepository.save(cast);
        }
        return cast;
    }
    @PostMapping("/cast")
    StudentToCourse newCourse(@RequestBody String str) throws JsonProcessingException {

        StudentToCourse cast;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(str);
        Course course = mapper.convertValue(node.get("course"), Course.class);
        Student student = mapper.convertValue(node.get("student"), Student.class);


        //curl -X POST localhost:8080/cast -H 'Content-type:application/json' -d '{"course":{"id":3,"name":"mat2"},"student":{"id":2,"name":"efe1"}}'

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