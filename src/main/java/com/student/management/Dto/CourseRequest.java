package com.student.management.Dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO for Course Creation and Update Request
 */
public class CourseRequest {

    @NotBlank(message = "Course code is required")
    @Size(min = 2, max = 20, message = "Course code should be between 2 and 20 characters")
    private String courseCode;

    @NotBlank(message = "Course name is required")
    @Size(min = 2, max = 100, message = "Course name should be between 2 and 100 characters")
    private String courseName;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 2000, message = "Description should be between 10 and 2000 characters")
    private String description;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotNull(message = "Fees is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fees must be greater than 0")
    private BigDecimal fees;

    @NotNull(message = "Maximum students is required")
    @Min(value = 1, message = "Maximum students must be at least 1")
    @Max(value = 500, message = "Maximum students should not exceed 500")
    private Integer maxStudents;

    // Getters and Setters
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }
}

