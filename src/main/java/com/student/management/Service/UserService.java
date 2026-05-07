package com.student.management.Service;

import com.student.management.Dto.ChangePasswordRequest;
import com.student.management.Entity.UserTable;
import com.student.management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ResponseEntity<?> saveUser(UserTable userTable){
        UserTable user = userRepository.save(userTable);
        if(user!=null)
        {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getAllStudents()
    {
        List<UserTable> allStudents = userRepository.findAll();
        return new ResponseEntity<>(allStudents,HttpStatus.FOUND);
    }

    // Get user by email
    public UserTable getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public ResponseEntity<?> changePassword(
            String username,
            ChangePasswordRequest request) {

        UserTable user = userRepository.findByEmail(username);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }

        // Verify current password
        if (!encoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            return ResponseEntity
                    .badRequest()
                    .body("Current password is incorrect");
        }

        // Check confirm password
        if (!request.getNewPassword()
                .equals(request.getConfirmPassword())) {

            return ResponseEntity
                    .badRequest()
                    .body("New password and confirm password do not match");
        }

        // Encrypt new password
        user.setPassword(
                encoder.encode(request.getNewPassword())
        );

        userRepository.save(user);

        return ResponseEntity
                .ok("Password changed successfully");
    }
}
