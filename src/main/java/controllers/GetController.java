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








    @GetMapping("/cast/list")
    public List<StudentToCourse> listCSTC(Model model) {

        return (List<StudentToCourse>) studentToCourseRepository.findAll();
    }
}
