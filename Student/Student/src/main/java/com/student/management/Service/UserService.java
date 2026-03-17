package com.student.management.Service;

import com.student.management.Entity.UserTable;
import com.student.management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

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


}
