package com.student.management.Service;

import com.student.management.Entity.Notes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NotesService {
    Notes uploadNotes(Notes notes, MultipartFile file);

    List<Notes> getAllNotes();

    Notes getNotesById(Long id);

    Notes updateNotes(Long id, Notes notes);

    void deleteNotes(Long id);
}
