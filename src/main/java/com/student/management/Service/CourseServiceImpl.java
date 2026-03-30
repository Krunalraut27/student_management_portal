package com.student.management.Service;

import com.student.management.Entity.Course;
import com.student.management.Exception.ResourceNotFoundException;
//import com.student.management.Exception.ResourceNotFoundException1;
import com.student.management.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

        private final CourseRepository repository;

        public CourseServiceImpl(CourseRepository repository) {
            this.repository = repository;
        }

        @Override
        public Course createCourse(Course course) {
            course.setCreatedAt(LocalDateTime.now());
            course.setCreatedBy(1L);

            course.setModifiedAt(LocalDateTime.now());
            course.setModifiedBy(1L);

            course.setDeleted(false);
            course.setActive(true);

            return repository.save(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return repository.findByDeletedFalse();
        }

        @Override
        public Course getCourseById(Long id) {

            Course course = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

            if(course.getDeleted())
                throw new ResourceNotFoundException("Course deleted");

            return course;
        }

        @Override
        public Course updateCourse(Long id, Course course) {

            Course existing = getCourseById(id);

            existing.setCourseCode(course.getCourseCode());
            existing.setCourseName(course.getCourseName());
            existing.setDescription(course.getDescription());
            existing.setDuration(course.getDuration());
            existing.setFees(course.getFees());
            existing.setMaxStudents(course.getMaxStudents());
            existing.setModifiedAt(LocalDateTime.now());

            return repository.save(existing);
        }

        @Override
        public void softDeleteCourse(Long id) {

            Course course = getCourseById(id);

            course.setDeleted(true);
            course.setActive(false);

            repository.save(course);
        }
    }

