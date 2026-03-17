package com.student.management.service;

import com.student.management.entity.Notes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NotesService {
    Notes uploadNotes(Notes notes, MultipartFile file);

    List<Notes> getAllNotes();

    Notes getNotesById(Long id);

    Notes updateNotes(Long id, Notes notes);

    void deleteNotes(Long id);
}
