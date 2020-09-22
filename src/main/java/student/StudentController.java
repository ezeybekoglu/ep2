package student;

import com.example.demo2.NotFoundException;
import controllers.Controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController extends Controller {

    @DeleteMapping(value = "/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

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

    @PostMapping("/student")
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

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
