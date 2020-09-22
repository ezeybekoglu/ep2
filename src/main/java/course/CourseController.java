package course;

import com.example.demo2.NotFoundException;
import controllers.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController extends Controller {

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

    @PostMapping("/course")
    Course newCourse(@RequestBody Course newCourse) {
        return courseRepository.save(newCourse);
    }

    @DeleteMapping(value = "/course/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }

    @PutMapping("/course/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {

        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return courseRepository.save(newCourse);
                });
    }
}
