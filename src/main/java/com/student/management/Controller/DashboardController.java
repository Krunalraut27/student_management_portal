package com.student.management.Controller;

import com.student.management.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard/countstudents")
    public long getStudentCount() {
        return dashboardService.getTotalStudents();
    }

    @GetMapping("/dashboard/countcourses")
    public long getCourseCount() {
        return dashboardService.getTotalCourses();
    }
}
