package com.student.management.Controller;

import com.student.management.Entity.Course;
import com.student.management.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/course")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCourse(@Valid @RequestBody Course course) {
        return service.createCourse(course);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/getByid/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id,
                                          @Valid @RequestBody Course course) {
        return service.updateCourse(id, course);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        return service.softDeleteCourse(id);
    }
}


