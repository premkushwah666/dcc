package com.dcc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dcc.ApiResponse;

//@RestController
//@RequestMapping("/assignment")
//public class AssignmentController {
//
//	@PostMapping("/add_assignment")
//	public ResponseEntity<?> addTask(@RequestBody MultipartFile[] mpf)
//	{
//		
//		return null;
//	}

import com.dcc.entity.Assignment;
import com.dcc.service.AssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/assignments")//localhost:8080
@CrossOrigin(origins = "*")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<ApiResponse> createAssignment(
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
        try {
        assignmentService.createAssignment(title, description, deadline, multipartFile);
        return new ResponseEntity<>(new ApiResponse("Assignment created"),HttpStatus.CREATED);
        }catch(IOException e)
        {
        	 e.printStackTrace(); 
             return new ResponseEntity<>(new ApiResponse("Assignment not created"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Assignment> getAllAssignments()
    {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignmentById(@PathVariable int id)
    {
    	Assignment assignment=assignmentService.getAssignmentById(id).orElse(null);
    	if(assignment==null)
    		return new ResponseEntity<>(new ApiResponse("Assignment not found"),HttpStatus.NOT_FOUND);
    	else
    		return ResponseEntity.ok().body(assignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAssignment(@PathVariable int id)
    {
        assignmentService.deleteAssignment(id);
        return new ResponseEntity<>(new ApiResponse("Assignment deleted"),HttpStatus.OK);
    }
}
