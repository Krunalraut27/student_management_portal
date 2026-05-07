package com.student.management.Controller;

import com.student.management.Entity.Students;
import com.student.management.Entity.UserTable;
import com.student.management.JwtToken.JwtUtil;
import com.student.management.Service.StudentService;
import com.student.management.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;


    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerNewStudent(@Valid @RequestBody Students student)
    {
        return studentService.registerNewStudent(student);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Students>> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id)
    {
        return studentService.getStudentById(id);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Students> updateStudentById(@PathVariable Long id,@RequestBody Students std)
    {
        return studentService.updatedStudentDetails(id, std);
    }

    @DeleteMapping("deleteStudent/{id}")
    public ResponseEntity<Students> softDeleteStudent(@PathVariable Long id)
    {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/myProfile")
    public ResponseEntity<?> getStudentProfile(
            @RequestHeader("Authorization") String authHeader) {

        try {

            // Remove Bearer
            String token = authHeader.substring(7);

            // Extract username from JWT
            String username = jwtUtil.extractUsername(token);

            // Fetch user from DB
            UserTable user = userService.getUserByEmail(username);

            if (user == null) {
                return ResponseEntity
                        .badRequest()
                        .body("User not found");
            }

            return ResponseEntity.ok(user);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid Token");
        }
    }
}
