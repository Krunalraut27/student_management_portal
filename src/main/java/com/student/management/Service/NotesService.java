package com.student.management.Service;

import com.student.management.Entity.Course;
import com.student.management.Entity.Notes;
import com.student.management.Entity.UserTable;
import com.student.management.Repository.UserRepo;
import com.student.management.Repository.NotesRepository;
import com.student.management.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

@Service
public class NotesService {

    @Autowired
    private NotesRepository repo;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private CourseService courseService;

    private String uploadDir = "C:/notes_upload/";

    public ResponseEntity<?> uploadNotes(Notes notes, MultipartFile file) {
        try {
            String email = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

            UserTable user = userRepository.findByEmail(email);
            notes.setUploadedBy(user.getId());

            File folder = new File(uploadDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            File dest = new File(filePath);
            file.transferTo(dest);

            Course course = courseService.getCourseEntityById(notes.getCourse().getId());
            notes.setCourse(course);

            notes.setFile_name(fileName);
            notes.setFile_path(filePath);
            notes.setFile_type(file.getContentType());
            notes.setFile_size(file.getSize());

            return ResponseEntity.status(201).body(repo.save(notes));

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<?> getAllNotes() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return ResponseEntity.ok(repo.findByIsActiveTrue());
        }

        UserTable user = userRepository.findByEmail(email);
        Long studentCourseId = user.getStudent().getCourse().getId();

        return ResponseEntity.ok(repo.findByCourse_IdAndIsActiveTrue(studentCourseId));
    }

    public ResponseEntity<?> getNotesById(Long id) {

        Notes notes = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes not found"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return ResponseEntity.ok(notes);
        }

        UserTable user = userRepository.findByEmail(email);
        Long studentCourseId = user.getStudent().getCourse().getId();

        if (!studentCourseId.equals(notes.getCourse().getId())) {
            throw new AccessDeniedException("You can only access your course notes");
        }

        return ResponseEntity.ok(notes);
    }

    public ResponseEntity<?> updateNotes(Long id, Notes notes) {

        Notes existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes not found"));

        existing.setTitle(notes.getTitle());
        existing.setDescription(notes.getDescription());
        existing.setSubject_id(notes.getSubject_id());
        existing.setCourse(notes.getCourse());

        return ResponseEntity.ok(repo.save(existing));
    }

    public ResponseEntity<?> deleteNotes(Long id) {

        Notes notes = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes not found"));

        notes.setActive(false);
        repo.save(notes);

        return ResponseEntity.ok("Notes deleted (Soft Delete)");
    }
}
