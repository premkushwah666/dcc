package com.dcc.service;

import com.dcc.Enumiration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dcc.entity.User;
import com.dcc.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public User createUser(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Enumiration.Role.STUDENT);
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
	
	public User findByEmail(String email)
	{
		return userRepository.findByemail(email);
	}
	
	public boolean deleteByEmail(String email)
	{
		userRepository.deleteByemail(email);
		return true;
	}
	
}
