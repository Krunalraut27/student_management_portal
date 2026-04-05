package com.student.management.Service;

import com.student.management.entity.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class NotesService {
    @Autowired
    private com.student.management.repository.NotesRepository repo;

    private String uploadDir = "C:/notes_upload/";


    public Notes uploadNotes(Notes notes, MultipartFile file) {

        try {

            File folder = new File(uploadDir);

            if(!folder.exists()){
                folder.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            notes.setFile_name(file.getOriginalFilename());
            notes.setFile_path(filePath);
            notes.setFile_type(file.getContentType());
            notes.setFile_size(file.getSize());
            return repo.save(notes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed");
        }
    }

    public List<Notes> getAllNotes() {
        return repo.findByIsActiveTrue();
    }

    public Notes getNotesById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new com.student.management.exception.ResourceNotFoundException("Notes not found"));
    }

    public Notes updateNotes(Long id, Notes notes) {
        Notes existing = getNotesById(id);
        existing.setTitle(notes.getTitle());
        existing.setDescription(notes.getDescription());
        existing.setSubject_id(notes.getSubject_id());
        existing.setCourse_id(notes.getCourse_id());
        return repo.save(existing);
    }

    public void deleteNotes(Long id) {
        Notes notes = getNotesById(id);
        notes.setActive(false);
        repo.save(notes);
    }
}
