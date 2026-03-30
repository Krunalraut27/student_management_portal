package com.student.management.Repository;

import com.student.management.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes,Long> {
    List<Notes> findByIsActiveTrue();
}
