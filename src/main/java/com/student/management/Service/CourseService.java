package com.student.management.Service;

import com.student.management.Entity.Course;
import com.student.management.Entity.UserTable;
import com.student.management.Exception.ResourceNotFoundException;
import com.student.management.Repository.CourseRepository;
import com.student.management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CourseService {

    private final CourseRepository repository;

    @Autowired
    private UserRepo userRepository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> createCourse(Course course) {
        course.setCreatedAt(LocalDateTime.now());

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserTable user = userRepository.findByEmail(email);

        course.setCreatedBy(user.getId());
        course.setModifiedAt(LocalDateTime.now());
        course.setModifiedBy(user.getId());
        course.setActive(true);

        return ResponseEntity.status(201).body(repository.save(course));
    }

    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(repository.findByActiveTrue());
    }

    public ResponseEntity<?> getCourseById(Long id) {
        return ResponseEntity.ok(getCourseEntityById(id));
    }

    public Course getCourseEntityById(Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (course.getActive() == null || !course.getActive()) {
            throw new ResourceNotFoundException("Course not found or inactive");
        }

        return course;
    }

    public ResponseEntity<?> updateCourse(Long id, Course course) {
        Course existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        existing.setCourseCode(course.getCourseCode());
        existing.setCourseName(course.getCourseName());
        existing.setDescription(course.getDescription());
        existing.setDuration(course.getDuration());
        existing.setFees(course.getFees());
        existing.setMaxStudents(course.getMaxStudents());
        existing.setModifiedAt(LocalDateTime.now());

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserTable user = userRepository.findByEmail(email);
        existing.setModifiedBy(user.getId());

        return ResponseEntity.ok(repository.save(existing));
    }

    public ResponseEntity<?> softDeleteCourse(Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setModifiedAt(LocalDateTime.now());

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserTable user = userRepository.findByEmail(email);
        course.setModifiedBy(user.getId());
        course.setActive(false);

        repository.save(course);

        return ResponseEntity.ok("Course deleted successfully");
    }
}

