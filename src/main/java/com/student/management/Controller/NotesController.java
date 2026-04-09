package com.student.management.Controller;

import com.student.management.Entity.Course;
import com.student.management.Entity.Notes;
import com.student.management.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<Notes> uploadNotes(
            @RequestParam("file") MultipartFile file,
            @RequestParam("note_code") String noteCode,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("subject_id") Long subjectId,
            @RequestParam("course_id") Long courseId)
    {

        Notes notes = new Notes();
        notes.setNote_code(noteCode);
        notes.setTitle(title);
        notes.setDescription(description);
        notes.setSubject_id(subjectId);

        Course course = new Course();
        course.setId(courseId);
        notes.setCourse(course);
        Notes savedNote = service.uploadNotes(notes, file);

        return ResponseEntity.ok(savedNote);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping("/getAll")
    public ResponseEntity<List<Notes>> getAllNotes() {
        return ResponseEntity.ok(service.getAllNotes());
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Notes> getNotesById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNotesById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Notes> updateNotes(@PathVariable Long id,
                                             @RequestBody Notes notes) {
        return ResponseEntity.ok(service.updateNotes(id, notes));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotes(@PathVariable Long id) {
        service.deleteNotes(id);
        return ResponseEntity.ok("Notes deleted (Soft Delete)");
    }
}