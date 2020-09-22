package controllers;

import course.Course;
import student.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutController extends Controller {

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

}
