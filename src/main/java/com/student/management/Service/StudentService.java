package com.student.management.Service;

import com.student.management.Entity.Students;
import com.student.management.Entity.UserTable;
import com.student.management.Exception.StudentNotFoundException;
import com.student.management.Repository.StudentRepository;
import com.student.management.Repository.UserRepo;
import com.student.management.Utility.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;


    public ResponseEntity<Students> registerNewStudent(Students student)
    {
        long count = studentRepository.count();
        int next = (int) count + 1;
        String code = String.format("STUD%04d", next);
        student.setStudentCode(code);

        String password = PasswordGenerator.generatePassword();

        student.setCreatedAt(LocalDateTime.now());
        student.setIsActive(true);
        Students savedStudent = studentRepository.save(student);

        UserTable user = new UserTable();
        user.setRole("ROLE_STUDENT");
        user.setEmail(student.getEmail());
        user.setPassword(encoder.encode(password));
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        user.setStudent(savedStudent);

        userRepository.save(user);

        emailService.sendPasswordToEmail(student.getEmail(), password);

        return new ResponseEntity<Students>(savedStudent,HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Students>> getAllStudents()
    {
        List<Students> allStud = studentRepository.findAll();
        return new ResponseEntity<>(allStud, HttpStatus.OK);
    }

    public ResponseEntity<?> getStudentById(Long id) {
        Optional<Students> stdById = studentRepository.findById(id);
        if (stdById.isPresent()) {
            return new ResponseEntity<>(stdById.get(), HttpStatus.OK);
        }
        else
        {
            throw new StudentNotFoundException("Student not found with id" + id);
        }
    }

    public ResponseEntity<Students> updatedStudentDetails(Long id, Students std) {
        Optional<Students> stdById = studentRepository.findById(id);
        Students stdToUpdate = stdById.get();

        stdToUpdate.setFirstName(std.getFirstName());
        stdToUpdate.setLastName(std.getLastName());
        stdToUpdate.setPhone(std.getPhone());
        stdToUpdate.setEmail(std.getEmail());
        stdToUpdate.setAddressLine1(std.getAddressLine1());
        stdToUpdate.setAddressLine2(std.getAddressLine2());
        stdToUpdate.setCity(std.getCity());
        stdToUpdate.setState(std.getState());
        stdToUpdate.setPostalCode(std.getPostalCode());
        stdToUpdate.setCountry(std.getCountry());
        stdToUpdate.setModifiedAt(std.getModifiedAt());
        stdToUpdate.setModifiedBy(std.getModifiedBy());

        Students savedStudent = studentRepository.save(stdToUpdate);
        return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    }

    public ResponseEntity<Students> deleteStudent(Long id, Students std)
    {
        Optional<Students> stdById = studentRepository.findById(id);
        Students stdDelete = stdById.get();
        stdDelete.setIsActive(false);
        stdDelete.setModifiedAt(LocalDateTime.now());
        stdDelete.setModifiedBy(std.getModifiedBy());
        Students deletedStudent = studentRepository.save(stdDelete);
        return new ResponseEntity<>(deletedStudent,HttpStatus.OK);
    }
}
