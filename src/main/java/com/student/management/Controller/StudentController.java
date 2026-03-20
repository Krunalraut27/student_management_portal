package com.student.management.Controller;

import com.student.management.Entity.Students;
import com.student.management.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("registerStudent")
    public ResponseEntity<?> registerNewStudent(@Valid @RequestBody Students student)
    {
        return studentService.registerNewStudent(student);
    }

    @GetMapping("getAllStudents")
    public ResponseEntity<List<Students>> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @GetMapping("getStudentById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id)
    {
        return studentService.getStudentById(id);
    }

    @PutMapping("updateStudent/{id}")
    public ResponseEntity<Students> updateStudentById(@PathVariable Long id,@RequestBody Students std)
    {
        return studentService.updatedStudentDetails(id, std);
    }

    @DeleteMapping("deleteStudent/{id}")
    public ResponseEntity<Students> softDeleteStudent(@PathVariable Long id, Students std)
    {
        return studentService.deleteStudent(id,std);
    }
}
