package com.dcc.service;

import com.dcc.Enumiration;
import com.dcc.Exception.ApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.generic.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dcc.entity.User;
import com.dcc.repository.UserRepository;
import com.dcc.util.StringUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> getAllUsers()
	{
		return new ArrayList<>(userRepository.findAll());
	}
	
	public User createUser(User user) throws ApiException
	{
		if(user!=null)
		{
			if(StringUtil.isNullOrEmplty(user.getUserName()))
			{
				throw new ApiException("Missing User Name");
			}
			if(StringUtil.isNullOrEmplty(user.getEmail()))
			{
		        throw new ApiException("Missing Email");
			}
			if(StringUtil.isNullOrEmplty(user.getPassword()))
			{
				throw new ApiException("Missing Password");
			}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Enumiration.Role.STUDENT);
		return userRepository.save(user);
		}
		throw new ApiException("User can not created");
	}
	
	public User createUserByAdmin(User user) throws ApiException
	{
		if(user!=null)
		{
			if(StringUtil.isNullOrEmplty(user.getUserName()))
			{
				throw new ApiException("Missing User Name");
			}
			if(StringUtil.isNullOrEmplty(user.getEmail()))
			{
		        throw new ApiException("Missing Email");
			}
			if(StringUtil.isNullOrEmplty(user.getPassword()))
			{
				throw new ApiException("Missing Password");
			}
			if(user.getRole()==null)
			{
				throw new ApiException("Missing Role");
			}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//user.setRole(Enumiration.Role.STUDENT);
		return userRepository.save(user);
		}
		throw new ApiException("User can not created");
	}
	
	public User updateUser(User user)
	{
		return userRepository.save(user);
	}
	
	public Optional<User> findById(Integer id)
	{
		return userRepository.findById(id);
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
	
	public boolean DeleteByemail(String email)
	{
		userRepository.deleteByemail(email);
		return true;
	}
	
}
