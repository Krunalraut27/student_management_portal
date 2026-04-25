package com.student.management.Service;

import com.student.management.Repository.CourseRepository;
import com.student.management.Repository.NotesRepository;
import com.student.management.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private NotesRepository notesRepository;

    public ResponseEntity<?> getTotalStudents() {
        return ResponseEntity.ok(studentRepository.count());
    }

    public ResponseEntity<?> getTotalCourses() {
        return ResponseEntity.ok(courseRepository.count());
    }

    public ResponseEntity<?> getTotalNotes() {
        return ResponseEntity.ok(notesRepository.count());
    }
}