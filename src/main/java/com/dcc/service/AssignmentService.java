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

    public Assignment createAssignment(String title, String description, String deadlineStr, MultipartFile[] files) throws IOException {
       
        LocalDate deadline = LocalDate.parse(deadlineStr);

       
        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setDeadline(deadline);

        List<AssignmentFile> fileList = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String storedFileName = UUID.randomUUID() + fileExtension;

             
                Path filePath = Paths.get(FILE_STORAGE_PATH + storedFileName);

             
                Files.createDirectories(filePath.getParent());

              
                Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE_NEW);

               
                AssignmentFile assignmentFile = new AssignmentFile();
                assignmentFile.setOriginalFileName(originalFileName);
                assignmentFile.setStoredFileName(storedFileName);
                assignmentFile.setFileType(file.getContentType());
                assignmentFile.setFileSize(file.getSize());
                assignmentFile.setFilePath(filePath.toString());
                assignmentFile.setAssignment(assignment); 

                fileList.add(assignmentFile);
            }
        }

        assignment.setFiles(fileList);
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
