package com.student.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.student.management.Entity.Students;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long>

{

    // Add this new method to find only active students
    List<Students> findByIsActiveTrue();
    Students findByEmail(String email);
    boolean existsByPhone(String phoneNumber);
}
