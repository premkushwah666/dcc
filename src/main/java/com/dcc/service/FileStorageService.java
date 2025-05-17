package com.dcc.service;

// FileStorageService.java
//    uploads/
//            assignments/
//            submissions/
//            profile-pictures/

import com.dcc.entity.AssignmentFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload.path:uploads}")
    private String uploadPath;

    public Path storeFile(MultipartFile file, String subFolder) throws IOException {
        // Create directories if they don't exist
        Path uploadDir = Paths.get(uploadPath, subFolder);
        Files.createDirectories(uploadDir);

        // Normalize file name and add timestamp to prevent collisions
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        // Store the file
        Path targetLocation = uploadDir.resolve(storedFileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return targetLocation;
    }


    public List<AssignmentFile> storeAssignments(MultipartFile[] files){

        List<AssignmentFile> fileList = new ArrayList<>();
        String subFolder = "assignments";

        if (files != null) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        Path filePath = storeFile(file, subFolder);
                        AssignmentFile assignmentFile = new AssignmentFile();
                        assignmentFile.setOriginalFileName(file.getOriginalFilename());
                        assignmentFile.setStoredFileName(filePath.getFileName().toString());
                        assignmentFile.setFileType(file.getContentType());
                        assignmentFile.setFilePath(filePath.toString());
                        assignmentFile.setFileSize(file.getSize());
                        fileList.add(assignmentFile);
                    } catch (Exception e) {
                        System.out.println("error while storing file" + file);
                        e.printStackTrace();
                    }
                }
            }
        }
        return fileList;
    }

    public void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);
    }
}
