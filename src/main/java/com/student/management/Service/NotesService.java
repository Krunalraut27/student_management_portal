package com.student.management.Service;

import com.student.management.Entity.Notes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.io.File;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class NotesService {
    @Autowired
    private com.student.management.Repository.NotesRepository repo;

    private String uploadDir = "C:/notes_upload/";


    public Notes uploadNotes(Notes notes, MultipartFile file) {

        try {
            // 1. Validate file
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            // 2. Create folder if not exists
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }









            // 3. Clean filename (security)
            String originalFileName = Paths.get(file.getOriginalFilename()).getFileName().toString();

            // 4. Generate unique filename
            String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // 5. Resolve path safely
            String filePath = uploadDir + File.separator + uniqueFileName;

            // 6. Save file
            file.transferTo(new File(filePath));

            // 7. Set metadata
            notes.setFile_name(uniqueFileName);
            notes.setFile_path(filePath.toString());
            notes.setFile_type(file.getContentType());
            notes.setFile_size(file.getSize());

            return repo.save(notes);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    public List<Notes> getAllNotes() {
        return repo.findByIsActiveTrue();
    }

    public Notes getNotesById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new com.student.management.Exception.ResourceNotFoundException("Notes not found"));
    }

    public Notes updateNotes(Long id, Notes notes, MultipartFile file) {

        Notes existing = getNotesById(id);

        // ✅ Update basic fields
        existing.setNote_code(notes.getNote_code());
        existing.setTitle(notes.getTitle());
        existing.setDescription(notes.getDescription());
        existing.setSubject(notes.getSubject());
        existing.setCourse_id(notes.getCourse_id());
        existing.setUploadedby(notes.getUploadedby());

        // ✅ File update logic (ONLY if new file comes)
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();

                // Optional: avoid duplicate names
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

                String filePath = uploadDir + File.separator + uniqueFileName;

                File dest = new File(filePath);
                file.transferTo(dest);

                // Update file details
                existing.setFile_name(uniqueFileName);
                existing.setFile_path(filePath);
                existing.setFile_type(file.getContentType());
                existing.setFile_size(file.getSize());

            } catch (Exception e) {
                throw new RuntimeException("File update failed: " + e.getMessage());
            }
        }

        // ❗ If file is null → old file remains untouched

        return repo.save(existing);
    }

    public void deleteNotes(Long id) {
        Notes notes = getNotesById(id);
        notes.setActive(false);
        repo.save(notes);
    }


    public ResponseEntity<Resource> getFile(Long id) {

        Notes notes = getNotesById(id);

        try {
            Path path = Paths.get(uploadDir).resolve(notes.getFile_name()).normalize();

            if (!Files.exists(path)) {
                throw new RuntimeException("File not found: " + path);
            }

            if (!Files.isReadable(path)) {
                throw new RuntimeException("File not readable: " + path);
            }

            Resource resource = (Resource) new UrlResource(path.toUri());

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(
                            notes.getFile_type() != null
                                    ? notes.getFile_type()
                                    : "application/octet-stream"
                    ))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + notes.getFile_name() + "\"")
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException("Error loading file: " + e.getMessage());
        }
    }
}
