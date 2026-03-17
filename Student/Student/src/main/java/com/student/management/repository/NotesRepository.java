package com.student.management.repository;

import com.student.management.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes,Long> {
    List<Notes> findByIsActiveTrue();
}
