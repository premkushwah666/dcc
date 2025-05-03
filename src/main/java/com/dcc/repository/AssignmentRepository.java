package com.dcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcc.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment,Integer>{

}
