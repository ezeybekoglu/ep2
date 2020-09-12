package com.example.demo2.controllers;

import com.example.demo2.model.Course;
import com.example.demo2.model.CustomSTC;
import com.example.demo2.model.Student;
import com.example.demo2.model.StudentToCourse;
import com.example.demo2.repository.CourseRepository;
import com.example.demo2.repository.CustomSTCRepo;
import com.example.demo2.repository.STCRepository;
import com.example.demo2.repository.StudentRepository;
import com.example.demo2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private StudentService ss;

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public void addNewStudent(@RequestParam Map<String, String> requestParams){
        if(!requestParams.containsKey("name") || requestParams.get("name").isEmpty())
        {
            //parameters empty or not defined
            throw new IllegalArgumentException("{\"error\":\"At least one parameter is invalid or not supplied\"}");
        }
        Student student = new Student();
        student.setName(requestParams.get("name"));
        studentRepository.save(student);
    }

    @RequestMapping(value = "/student/update", method = RequestMethod.POST)
    public void updateStudent(@RequestParam Map<String, String> requestParams){
        if(!requestParams.containsKey("name") || requestParams.get("name").isEmpty()
            ||!requestParams.containsKey("id") || requestParams.get("id").isEmpty())
        {
            //parameters empty or not defined
            throw new IllegalArgumentException("{\"error\":\"At least one parameter is invalid or not supplied\"}");
        }

        Long studentId = Long.parseLong(requestParams.get("id"));
        Student studentToUpdate = studentRepository.findById(studentId).orElse(null);
        if(studentToUpdate != null) {
            //update name
            studentToUpdate.setName(requestParams.get("name"));
            studentRepository.save(studentToUpdate);
        }
    }
    @RequestMapping(value = "/student/delete", method = RequestMethod.POST)
    public void deleteStudent(@RequestParam Map<String, String> requestParams){
        //get id param
        Long studentId = Long.parseLong(requestParams.get("id"));
        Student studentToDelete = studentRepository.findById(studentId).orElse(null);
        //if id exists
        if(studentToDelete != null) {
            //delete student
            studentRepository.delete(studentToDelete);
        }
    }


    @RequestMapping(value = "/course/add", method = RequestMethod.POST)
    public void addNewCourse(@RequestParam Map<String, String> requestParams){
        if(!requestParams.containsKey("name") || requestParams.get("name").isEmpty())
        {
            //parameters empty or not defined
            throw new IllegalArgumentException("{\"error\":\"At least one parameter is invalid or not supplied\"}");
        }
        Course course = new Course();
        course.setName(requestParams.get("name"));
        courseRepository.save(course);
    }
    @RequestMapping(value = "/course/update", method = RequestMethod.POST)
    public void updateCourse(@RequestParam Map<String, String> requestParams){
        Long courseId = Long.parseLong(requestParams.get("id"));
        Course courseToUpdate = courseRepository.findById(courseId).orElse(null);
        if(courseToUpdate != null) {
            courseToUpdate.setName(requestParams.get("name"));
            courseRepository.save(courseToUpdate);
        }
    }
    @RequestMapping(value = "/course/delete", method = RequestMethod.POST)
    public void deleteCourse(@RequestParam Map<String, String> requestParams){
        Long courseId = Long.parseLong(requestParams.get("id"));
        Course courseToDelete = courseRepository.findById(courseId).orElse(null);
        if(courseToDelete != null) {
            courseRepository.delete(courseToDelete);
        }
    }

    @RequestMapping(value = "/stc/add", method = RequestMethod.POST)
    public void addSTC(@RequestParam Map<String, String> requestParams){
        Long studentID = Long.parseLong(requestParams.get("studentID"));
        Long courseID = Long.parseLong(requestParams.get("courseID"));

        Student student = studentRepository.findById(studentID).orElse(null);
        Course course = courseRepository.findById(courseID).orElse(null);
        if(course != null && student != null) {
            StudentToCourse stc = new StudentToCourse();
            stc.setCourseId(courseID);
            stc.setStudentId(studentID);
            stcRepository.save(stc);
        }
    }
    @RequestMapping(value = "/stc/update", method = RequestMethod.POST)
    public void updateSTC(@RequestParam Map<String, String> requestParams){
        if((!requestParams.containsKey("id") || !requestParams.containsKey("studentID")
                || !requestParams.containsKey("courseID"))
                || (requestParams.get("id").isEmpty()
                || requestParams.get("studentID").isEmpty()
                || requestParams.get("courseID").isEmpty())
        )
        {
            //parameters empty or not defined
            throw new IllegalArgumentException("{\"error\":\"At least one parameter is invalid or not supplied\"}");
        }
        Long stcId = Long.parseLong(requestParams.get("id"));
        Long studentID = Long.parseLong(requestParams.get("studentID"));
        Long courseID = Long.parseLong(requestParams.get("courseID"));

        Student student = studentRepository.findById(studentID).orElse(null);
        Course course = courseRepository.findById(courseID).orElse(null);
        if(course != null && student != null) {
            StudentToCourse stc = new StudentToCourse();
            stc.setId(stcId);
            stc.setCourseId(courseID);
            stc.setStudentId(studentID);
            stcRepository.save(stc);
        }
    }
    @RequestMapping(value = "/stc/delete", method = RequestMethod.POST)
    public void stcCourse(@RequestParam Map<String, String> requestParams){
        Long stcId = Long.parseLong(requestParams.get("id"));
        StudentToCourse stcToDelete = stcRepository.findById(stcId).orElse(null);
        if(stcToDelete != null) {
            //if id exists
            stcRepository.delete(stcToDelete);
        }
    }
    @RequestMapping(value = "/cast/add", method = RequestMethod.POST)
    public void addCAST(@RequestParam Map<String, String> requestParams){
        if(!requestParams.containsKey("studentName")
                ||   !requestParams.containsKey("courseName")
                ||    requestParams.get("studentName").isEmpty()
                ||    requestParams.get("courseName").isEmpty()
        )
        {
            //parameters empty or not defined
            throw new IllegalArgumentException("{\"error\":\"At least one parameter is invalid or not supplied\"}");
        }
        Student student,s;
        Course course, c;
        student = studentRepository.findByName(requestParams.get("studentName"));
        course = courseRepository.findByName(requestParams.get("courseName"));

        if( student == null) {
            s = new Student();
            s.setName(requestParams.get("studentName"));
            student=s;
        }
        if(course == null) {
            c = new Course();
            c.setName(requestParams.get("courseName"));
            course = c;
        }
        CustomSTC cast = new CustomSTC();
        cast.setStudent(student);

        cast.setCourse(course);
        castRepo.save(cast);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final String exceptionHandlerIllegalArgumentException(final IllegalArgumentException e) {
        return '"' + e.getMessage() + '"';
    }
}
