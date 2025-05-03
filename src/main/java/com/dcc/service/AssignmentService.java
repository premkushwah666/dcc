package com.dcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcc.Exception.ApiException;
import com.dcc.entity.Assignment;
import com.dcc.repository.AssignmentRepository;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentRepository assignmentRepository;
	
	public Assignment addAssignment(Assignment assignment)throws ApiException
	{
		if(assignment!=null)
			return assignmentRepository.save(assignment);
		
		throw new ApiException("Assignment Not added");
	}
	
}
