package com.student.management.Controller;

import com.student.management.Entity.Course;
import com.student.management.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/course")
public class CourseController {

        private final CourseService service;

        public CourseController(CourseService service) {
            this.service = service;
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/addCourse")
        public Course createCourse(@Valid @RequestBody Course course){
            return service.createCourse(course);
        }

        @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
        @GetMapping("/getAllCourse")
        public List<Course> getAllCourses(){
            return service.getAllCourses();
        }

        @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
        @GetMapping("/getCourseByid/{id}")
        public Course getCourse(@PathVariable Long id){
            return service.getCourseById(id);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/updateCourse/{id}")
        public Course updateCourse(@PathVariable Long id,
                                   @Valid @RequestBody Course course){

            return service.updateCourse(id, course);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/deleteCourse/{id}")
        public String deleteCourse(@PathVariable Long id){

            service.softDeleteCourse(id);
            return "Course deleted successfully (Soft Delete)";
        }
    }


