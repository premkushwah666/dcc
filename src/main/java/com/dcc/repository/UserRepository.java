package com.dcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcc.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByuserName(String name);
	
	User deleteByuserName(String name);
	
<<<<<<< HEAD
//	User findByemail(String email);
//	
//	User DeleteByemail(String email);
=======
	User findByemail(String email);
	
	User deleteByemail(String email);
>>>>>>> a82d87c87533672264451c9ac724c8f790db0a8b
}
