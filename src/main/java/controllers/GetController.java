package controllers;
import com.example.demo2.NotFoundException;
import course.Course;
import studentToCourse.StudentToCourse;
import student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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





    @GetMapping("/cast/list")
    public List<StudentToCourse> listCSTC(Model model) {

        return (List<StudentToCourse>) studentToCourseRepository.findAll();
    }
}
