package com.student.management.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.student.management.Entity.CourseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name="students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="student_code", unique=true)
    private String studentCode;

    @Column(name="first_name")
    @NotBlank
    @Size(min=2,max=100, message="First Name should not be blank")
    private String firstName;

    @Column(name="last_name")
    @NotBlank
    @Size(min=2,max=100, message="Last Name should not be blank")
    private String lastName;

    @Column(name="email", length=150, unique=true)
    @Email
    @NotBlank(message="Please Enter a Valid Email")
    private String email;

    @Column(name="phone", length=15, unique=true)
    @Pattern(regexp="^[0-9]{10}$", message="Phone number should be 10 digits")
    private String phone;

    private Integer age;

    private String gender;

    @ManyToOne
    @JoinColumn(name="course_id")
    private CourseEntity course;

    @Column(name="address_line1")
    @Size(max=255)
    private String addressLine1;

    @Column(name="address_line2")
    @Size(max=255)
    private String addressLine2;

    @Size(max=100)
    private String city;

    @Size(max=100)
    private String state;

    @Column(name="postal_code")
    @Size(max=20)
    private String postalCode;

    private String country;

    @Column(name="is_active")
    private Boolean isActive = true;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Column(name="password")
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }


    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }


    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }


    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
