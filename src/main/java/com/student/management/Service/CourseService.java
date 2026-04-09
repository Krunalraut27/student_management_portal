package com.student.management.Service;

import com.student.management.Entity.Course;
import com.student.management.Entity.UserTable;
import com.student.management.Exception.ResourceNotFoundException;
import com.student.management.Repository.CourseRepository;
import com.student.management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

        private final CourseRepository repository;

        @Autowired
        private UserRepo userRepository;

        public CourseService(CourseRepository repository) {
            this.repository = repository;
        }

        public Course createCourse(Course course) {
            course.setCreatedAt(LocalDateTime.now());
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserTable user = userRepository.findByEmail(email);
            course.setCreatedBy(user.getId());
            course.setModifiedAt(LocalDateTime.now());
            course.setModifiedBy(user.getId());
            course.setActive(true);

            return repository.save(course);
        }

        public List<Course> getAllCourses() {
        return repository.findByActiveTrue();
        }

        public Course getCourseById(Long id) {

        Course course = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (course.getActive() == null || !course.getActive()) {
                throw new ResourceNotFoundException("Course not found or inactive");
        }

        return course;
        }

        public Course updateCourse(Long id, Course course) {

            Course existing = getCourseById(id);

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

            return repository.save(existing);
        }
        public void softDeleteCourse(Long id) {

            Course course = getCourseById(id);
            course.setModifiedAt(LocalDateTime.now());

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserTable user = userRepository.findByEmail(email);
            course.setModifiedBy(user.getId());
            course.setActive(false);

            repository.save(course);
        }
}

