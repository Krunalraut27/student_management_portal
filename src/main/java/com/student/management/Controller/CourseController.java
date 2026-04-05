package com.student.management.Controller;


import com.student.management.Entity.Course;

import com.student.management.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CourseController {

        @Autowired
        private CourseService service;

        public CourseController(CourseService service) {
            this.service = service;
        }

        @PostMapping("/addCourse")
        public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course){
            Course createdCourse = service.createCourse(course);
            return ResponseEntity.ok(createdCourse);
        }

        @GetMapping("/getAllCourse")
        public ResponseEntity<List<Course>> getAllCourses(){
            List<Course> courses = service.getAllCourses();
            return ResponseEntity.ok(courses);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Course> getCourse(@PathVariable Long id){
            Course course = service.getCourseById(id);
            return ResponseEntity.ok(course);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course course){
            Course updatedCourse = service.updateCourse(id, course);
            return ResponseEntity.ok(updatedCourse);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCourse(@PathVariable Long id){
            service.softDeleteCourse(id);
            return ResponseEntity.ok("Course deleted successfully (Soft Delete)");
        }
    }
