package com.dcc.service;

import com.dcc.entity.Assignment;
import com.dcc.entity.AssignmentFile;
import com.dcc.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssignmentService {

    private static final String FILE_STORAGE_PATH = "uploads/";

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Assignment createAssignment(String title, String description, String deadlineStr, MultipartFile[] files) {
       
        LocalDate deadline = LocalDate.parse(deadlineStr);

        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setDeadline(deadline);
        List<AssignmentFile> assignmentFiles = fileStorageService.storeAssignments(files);

        // Seting the reference in each file
        for (AssignmentFile file : assignmentFiles) {
            file.setAssignment(assignment);
        }

        assignment.setFiles(assignmentFiles);

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> getAssignmentById(Integer id) {
        return assignmentRepository.findById(id);
    }

    public void deleteAssignment(Integer id) {
        assignmentRepository.findById(id).ifPresent(assignment -> {
            for (AssignmentFile file : assignment.getFiles()) {
                try {
                    Files.deleteIfExists(Paths.get(file.getFilePath()));
                } catch (IOException e) {
                    e.printStackTrace(); 
                }
            }
            assignmentRepository.deleteById(id);
        });
    }
}
