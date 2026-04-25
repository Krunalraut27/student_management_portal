package com.student.management.Controller;

import com.student.management.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/countstudents")
    public ResponseEntity<?> getStudentCount() {
        return dashboardService.getTotalStudents();
    }

    @GetMapping("/countcourses")
    public ResponseEntity<?> getCourseCount() {
        return dashboardService.getTotalCourses();
    }

    @GetMapping("/countnotes")
    public ResponseEntity<?> getNotesCount() {
        return dashboardService.getTotalNotes();
    }
}
