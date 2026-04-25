package com.student.management.Controller;

import com.student.management.Entity.Course;
import com.student.management.Entity.Notes;
import com.student.management.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadNotes(
            @RequestParam("file") MultipartFile file,
            @RequestParam("note_code") String noteCode,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("subject_id") Long subjectId,
            @RequestParam("course_id") Long courseId) {

        Notes notes = new Notes();
        notes.setNote_code(noteCode);
        notes.setTitle(title);
        notes.setDescription(description);
        notes.setSubject_id(subjectId);

        Course course = new Course();
        course.setId(courseId);
        notes.setCourse(course);

        return service.uploadNotes(notes, file);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllNotes() {
        return service.getAllNotes();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getNotesById(@PathVariable Long id) {
        return service.getNotesById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNotes(@PathVariable Long id,
                                         @RequestBody Notes notes) {
        return service.updateNotes(id, notes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNotes(@PathVariable Long id) {
        return service.deleteNotes(id);
    }
}