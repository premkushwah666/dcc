package com.dcc.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.dcc.entity.Assignment;
//
//public interface AssignmentRepository extends JpaRepository<Assignment,Integer>{

import com.dcc.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

}
