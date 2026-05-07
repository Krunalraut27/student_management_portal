package com.student.management.Service;

import com.student.management.Entity.Course;
import com.student.management.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;


    public Course createCourse(Course course) {
        course.setCreatedAt(LocalDateTime.now());
        course.setCreatedBy(1L);

        course.setModifiedAt(LocalDateTime.now());
        course.setModifiedBy(1L);
        course.setActive(true);

        return repository.save(course);
    }

    public List<Course> getAllCourses() {

        return repository.findAll();
    }

    public Course getCourseById(Long id) {

        Course course = repository.findById(id)
                .orElseThrow(() -> new com.student.management.Exception.ResourceNotFoundException1("Course not found"));

        if (!course.getActive())
            throw new com.student.management.Exception.ResourceNotFoundException1("Course not found or inactive");

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

        return repository.save(existing);
    }

    public void softDeleteCourse(Long id) {

        Course course = getCourseById(id);
        course.setActive(false);

        repository.save(course);
    }
}