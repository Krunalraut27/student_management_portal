package com.student.management.Controller;

import com.student.management.Service.NotesService;
import com.student.management.Entity.Notes;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.UrlResource;


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
            @RequestParam("subject") String subject,
            @RequestParam("course_id") Long courseId,
            @RequestParam("uploadedby") Long uploadedBy) {

        try {
            // 1. Create the Notes object and set the data from Postman
            Notes notes = new Notes();
            notes.setNote_code(noteCode);
            notes.setTitle(title);
            notes.setDescription(description);
            notes.setSubject(subject);
            notes.setCourse_id(courseId);
            notes.setUploadedby(uploadedBy);

            // 2. Call the service (handles the file saving and DB saving)
            Notes savedNote = service.uploadNotes(notes, file);

            return ResponseEntity.ok(savedNote);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }


    @GetMapping("/getAllNotes")
    public ResponseEntity<List<Notes>> getAllNotes(){

        return ResponseEntity.ok(service.getAllNotes());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Notes> getNotesById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNotesById(id));
    }



    @PutMapping("/updateNotes/{id}")
    public ResponseEntity<?> updateNotes(
            @PathVariable Long id,
            @RequestParam("note_code") String noteCode,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("subject") String subject,
            @RequestParam("course_id") Long courseId,
            @RequestParam("uploadedby") Long uploadedBy,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        Notes notes = new Notes();
        notes.setNote_code(noteCode);
        notes.setTitle(title);
        notes.setDescription(description);
        notes.setSubject(subject);
        notes.setCourse_id(courseId);
        notes.setUploadedby(uploadedBy);

        return ResponseEntity.ok(service.updateNotes(id, notes, file));
    }



    @DeleteMapping("/deleteNotes/{id}")
    public ResponseEntity<String> deleteNotes(@PathVariable Long id){
        service.deleteNotes(id);
        return ResponseEntity.ok("Notes deleted (Soft Delete)");
    }

    @GetMapping("/viewFile/{id}")
    public ResponseEntity<Resource> viewFile(@PathVariable Long id) {
        return service.getFile(id);
    }
}