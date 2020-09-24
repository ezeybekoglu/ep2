package com.example.demo2.course;

import com.example.demo2.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController  {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/com/example/demo2/controller")
    public List<Course> listCourse(Model model) {

        return (List<Course>) courseRepository.findAll();
    }
    // Single item

    @GetMapping("/com/example/demo2/controller/{id}")
    Course oneCourse(@PathVariable Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PostMapping("/com/example/demo2/controller")
    Course newCourse(@RequestBody Course newCourse) {
        return courseRepository.save(newCourse);
    }

    @DeleteMapping(value = "/com/example/demo2/controller/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }

    @PutMapping("/com/example/demo2/controller/{id}")
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
