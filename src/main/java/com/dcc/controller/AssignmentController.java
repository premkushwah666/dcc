package com.dcc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

	@PostMapping("/add_assignment")
	public ResponseEntity<?> addTask(@RequestBody MultipartFile[] mpf)
	{
		
		return null;
	}
}
