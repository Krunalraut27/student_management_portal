package com.student.management.Controller;
import com.student.management.Dto.UserLoginRequest;   //DTO used for login request
import com.student.management.Entity.UserTable;       //Entity class for database table
import com.student.management.JwtToken.JwtUtil;       //Class used to generate JWT token
import com.student.management.Service.UserService;    //Service layer that handles business logic
import org.springframework.beans.factory.annotation.Autowired;    //Used for Dependency Injection.
import org.springframework.http.ResponseEntity;      //Used to return HTTP responses like, 200 OK,404 Not Found,500 Error
import org.springframework.security.authentication.*;        //Used for authentication process.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;         //Used to create REST APIs.

import java.time.LocalDateTime;           //Used to store date and time.

@RestController       //Creates REST API controller
@CrossOrigin("*")
public class AuthController {

    @Autowired         //inject one class object into another class
    AuthenticationManager authenticationManager;     //verify userid and password - inbuilt interface in spring security

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    BCryptPasswordEncoder encoder;        //Encrypt password before saving in database.

    @Autowired
    UserService userService;


    @PostMapping("/login")                       //Handles POST requests
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request){               //Accepts JSON request body (@Requestbody)

        authenticationManager.authenticate(                      //received here
                new UsernamePasswordAuthenticationToken(         //token is created here
                        request.getUsername(),                   //spring securitt\y check username
                        request.getPassword()                    //password check
                        // if valid authentication success - if not exception thrown
                )
        );

        return ResponseEntity.ok(jwtUtil.generateToken(request.getUsername()));     //token is created  //Client will use it as a Bearer token
    }

    //Fetch all students from database.
//    @GetMapping("/getAllStudents")                  //Handles GET requests
//    public ResponseEntity<?> getAllStudents()
//    {
//        return userService.getAllStudents();
//    }



    @PostMapping("/register")                      //
    public ResponseEntity<?> register(@RequestBody UserTable user){
        user.setModifiedAt(LocalDateTime.now());    //Sets last modified date.
        user.setCreatedAt(LocalDateTime.now());

        user.setPassword(encoder.encode(user.getPassword()));


        return userService.saveUser(user);   //Service saves the user and returns response.


    }
}