package com.student.management.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.student.management.Entity.Students;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Column(name="first_name")
    @NotBlank
    @Size(min=2,max=100, message="First Name should not be blank")
    private String firstName;

    @Column(name="last_name")
    @NotBlank
    @Size(min=2,max=100, message="Last Name should not be blank")
    private String lastName;

    @JoinColumn(unique = true)
    @Email
    @NotBlank(message = "Please Enter Valid Email")
    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name="student_id")
    private Students student;

    @Column(name="is_active")
    private Boolean isActive=true;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public Long getId()
    {
        return id;
    }

    public String getRole() {

        return role;
    }

    public String getEmail() {

        return email;
    }

    public String getPassword() {
        return password;
    }

    public Students getStudent() {
        return student;
    }

    public Boolean getIsActive()
    {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    public LocalDateTime getModifiedAt() {

        return modifiedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
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
}
