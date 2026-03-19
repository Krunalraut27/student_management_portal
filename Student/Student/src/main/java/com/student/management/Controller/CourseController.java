package com.student.management.Controller;


import com.student.management.CourseService;
import com.student.management.Entity.Course;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

        private final CourseService service;

        public CourseController(CourseService service) {
            this.service = service;
        }

        @PostMapping("/addCourse")
        public Course createCourse(@Valid @RequestBody Course course){
            return service.createCourse(course);
        }

        @GetMapping("/getAllCourse")
        public List<Course> getAllCourses(){
            return service.getAllCourses();
        }

        @GetMapping("/{id}")
        public Course getCourse(@PathVariable Long id){
            return service.getCourseById(id);
        }

        @PutMapping("/{id}")
        public Course updateCourse(@PathVariable Long id,
                                   @Valid @RequestBody Course course){

            return service.updateCourse(id, course);
        }

        @DeleteMapping("/{id}")
        public String deleteCourse(@PathVariable Long id){

            service.softDeleteCourse(id);

            return "Course deleted successfully (Soft Delete)";
        }
    }


