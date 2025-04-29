package com.dcc.controller;

import com.dcc.entity.Assignment;
import com.dcc.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> getAssignmentById(Integer id) {
        return assignmentRepository.findById(id);
    }

    public void deleteAssignment(Integer id) {
        assignmentRepository.deleteById(id);
    }
}
