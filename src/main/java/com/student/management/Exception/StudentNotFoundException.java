package com.student.management.Exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message)
    {
        super(message);
    }
}
