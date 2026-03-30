package com.student.management.Controller;

import com.student.management.Service.NotesService;
import com.student.management.Entity.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
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
            @RequestParam("course_id") Long courseId,
            @RequestParam("uploadedby") Long uploadedBy) {

        try {
            // 1. Create the Notes object and set the data from Postman
            Notes notes = new Notes();
            notes.setNote_code(noteCode);
            notes.setTitle(title);
            notes.setDescription(description);
            notes.setSubject_id(subjectId);
            notes.setCourse_id(courseId);
            notes.setUploadedby(uploadedBy);

            // 2. Call the service (handles the file saving and DB saving)
            Notes savedNote = service.uploadNotes(notes, file);

            return ResponseEntity.ok(savedNote);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }


    @GetMapping("/all")
    public List<Notes> getAllNotes(){

        return service.getAllNotes();
    }



    @GetMapping("/{id}")
    public Notes getNotesById(@PathVariable Long id) {
        return service.getNotesById(id);
    }



    @PutMapping("/{id}")
    public Notes updateNotes(@PathVariable Long id,
                             @RequestBody Notes notes){
        return service.updateNotes(id,notes);
    }



    @DeleteMapping("/{id}")
    public String deleteNotes(@PathVariable Long id){
        service.deleteNotes(id);
        return "Notes deleted (Soft Delete)";
    }
}