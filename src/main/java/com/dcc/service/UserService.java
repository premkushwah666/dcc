package com.dcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcc.entity.User;
import com.dcc.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 

	public User createUser(User user)
	{
			return userRepository.save(user);
	}

	public User findByUserName(String name) 
	{
		return userRepository.findByuserName(name);
	}
	
	public boolean deleteUser(String name)
	{
		userRepository.deleteByuserName(name);
		return true;
	}
	
//	public User findByEmail(String email)
//	{
//		return userRepository.findByemail(email);
//	}
//	
//	public boolean deleteByEmail(String email)
//	{
//		userRepository.DeleteByemail(email);
//		return true;
//	}
	
}
