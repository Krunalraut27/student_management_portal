package com.student.management.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.student.management.exception.ResourceNotFoundException1;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler1 {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler1.class);

        @ExceptionHandler(ResourceNotFoundException1.class)
        public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException1 ex){

            logger.error("Resource not found: {}", ex.getMessage(), ex);
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex){

            logger.error("Validation error: {}", ex.getMessage(), ex);
            Map<String, Object> errors = new HashMap<>();
            errors.put("error", "Validation failed");
            Map<String, String> fieldErrors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error ->
                    fieldErrors.put(error.getField(), error.getDefaultMessage()));
            errors.put("fieldErrors", fieldErrors);

            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
    }
