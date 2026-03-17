package com.student.management.service;

import com.student.management.entity.Notes;
import com.student.management.repository.NotesRepository;
import com.student.management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class NotesServiceImpl implements NotesService{

        @Autowired
        private NotesRepository repo;

     private String uploadDir = "C:/notes_upload/";


        @Override
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

        @Override
        public List<Notes> getAllNotes() {
            return repo.findByIsActiveTrue();
        }

        @Override
        public Notes getNotesById(Long id) {
            return repo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Notes not found"));
        }

        @Override
        public Notes updateNotes(Long id, Notes notes) {
            Notes existing = getNotesById(id);
            existing.setTitle(notes.getTitle());
            existing.setDescription(notes.getDescription());
            existing.setSubject_id(notes.getSubject_id());
            existing.setCourse_id(notes.getCourse_id());
            return repo.save(existing);
        }

        @Override
        public void deleteNotes(Long id) {
            Notes notes = getNotesById(id);
            notes.setActive(false);
            repo.save(notes);
        }
    }

