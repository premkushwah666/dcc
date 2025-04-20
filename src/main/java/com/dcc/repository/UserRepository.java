package com.dcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcc.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByuserName(String name);
	
	User deleteByuserName(String name);
	
	User findByemail(String email);	
	
	User deleteByemail(String email);
}
