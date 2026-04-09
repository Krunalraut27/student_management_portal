package com.student.management.Service;

import com.student.management.Entity.Course;
import com.student.management.Entity.Notes;
import com.student.management.Entity.UserTable;
import com.student.management.Repository.CourseRepository;
import com.student.management.Repository.UserRepo;
import com.student.management.Repository.NotesRepository;
import com.student.management.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.List;

@Service
public class NotesService{

        @Autowired
        private NotesRepository repo;

        @Autowired
        private UserRepo userRepository;

        @Autowired
        private CourseService courseService;

        private String uploadDir = "C:/notes_upload/";

        public Notes uploadNotes(Notes notes, MultipartFile file) {

            try {
                //Get logged-in user
                String email = SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

                UserTable user = userRepository.findByEmail(email);
                notes.setUploadedBy(user.getId());

                // create folder if not exists
                File folder = new File(uploadDir);
                if(!folder.exists()){
                    folder.mkdirs();
                }

                // unique file name (important)
                String fileName = file.getOriginalFilename();
                String filePath = uploadDir + fileName;
                File dest = new File(filePath);
                file.transferTo(dest);

                // fetch valid course (IMPORTANT)
                Course course = courseService.getCourseById(notes.getCourse().getId());
                notes.setCourse(course);

                notes.setFile_name(file.getOriginalFilename());
                notes.setFile_path(filePath);
                notes.setFile_type(file.getContentType());
                notes.setFile_size(file.getSize());
                return repo.save(notes);
            }
            catch (Exception e) { e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        public List<Notes> getAllNotes() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return repo.findByIsActiveTrue();
        }

        UserTable user = userRepository.findByEmail(email);

        Long studentCourseId = user.getStudent().getCourse().getId();

        return repo.findByCourse_IdAndIsActiveTrue(studentCourseId);
    }

    public Notes getNotesById(Long id) {

        Notes notes = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes not found"));

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return notes;
        }

        UserTable user = userRepository.findByEmail(email);

        Long studentCourseId = user.getStudent().getCourse().getId();

        if (!studentCourseId.equals(notes.getCourse().getId())) {
            throw new AccessDeniedException("You can only access your course notes");
        }

        return notes;
    }

        public Notes updateNotes(Long id, Notes notes) {
            Notes existing = getNotesById(id);
            existing.setTitle(notes.getTitle());
            existing.setDescription(notes.getDescription());
            existing.setSubject_id(notes.getSubject_id());
            existing.setCourse(notes.getCourse());
            return repo.save(existing);
        }

        public void deleteNotes(Long id) {
            Notes notes = getNotesById(id);
            notes.setActive(false);
            repo.save(notes);
        }
    }

