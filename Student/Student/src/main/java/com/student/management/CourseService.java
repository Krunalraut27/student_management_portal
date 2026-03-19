package com.student.management;



import com.student.management.Entity.Course;

import java.util.List;


public interface CourseService {


    Course createCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course updateCourse(Long id, Course course);

    void softDeleteCourse(Long id);}