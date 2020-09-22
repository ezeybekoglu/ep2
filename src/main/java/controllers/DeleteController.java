package controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController extends Controller {


    @DeleteMapping(value = "/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }



    @DeleteMapping(value = "/cast/{id}")
    public void deleteCast(@PathVariable Long id) {
        studentToCourseRepository.deleteById(id);
    }
}
