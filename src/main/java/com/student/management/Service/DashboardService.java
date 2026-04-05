package com.student.management.Service;


import com.student.management.Repository.CourseRepository;
import com.student.management.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public long getTotalStudents() {
        return studentRepository.count();
    }

    public long getTotalCourses() {
        return courseRepository.count();
    }
}