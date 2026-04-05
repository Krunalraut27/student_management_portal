package com.student.management.Controller;

import com.student.management.Entity.Students;
import com.student.management.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerNewStudent(@Valid @RequestBody Students student)
    {
        return studentService.registerNewStudent(student);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Students>> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id)
    {
        return studentService.getStudentById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Students> updateStudentById(@PathVariable Long id,@RequestBody Students std)
    {
        return studentService.updatedStudentDetails(id, std);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Students> softDeleteStudent(@PathVariable Long id, Students std)
    {
        return studentService.deleteStudent(id,std);
    }
}
