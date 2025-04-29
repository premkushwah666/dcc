package com.dcc.controller;

import com.dcc.entity.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/assignments")//localhost:8080
@CrossOrigin(origins = "*")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(
            @RequestParam("title") String title,
            @RequestParam String description,
            @RequestParam("deadline") String deadline,
            @RequestParam("files")MultipartFile[] multipartFile
    ) {
       // Assignment saved = assignmentService.createAssignment(assignment);
       // return new ResponseEntity<>(saved, HttpStatus.CREATED);
        System.out.println(multipartFile.length);
        System.out.println(Arrays.stream(multipartFile).count());
        System.out.println(multipartFile[0].getContentType());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable int id) {
        return assignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable int id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
