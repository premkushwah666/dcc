package com.dcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcc.StringUtil;
import com.dcc.Exception.ApiException;
import com.dcc.Validation.Valide;
import com.dcc.entity.User;
import com.dcc.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 

	public User createUser(User user)
	{

		if(StringUtil.isNullOrEmplty(user.getEmail()))
		{
			throw new ApiException("Missing Email");
		}
		if(StringUtil.isNullOrEmplty(user.getEmail()))
		{
	        throw new ApiException("Missing Name");
		}
		if(StringUtil.isNullOrEmplty(user.getEmail()))
		{
			throw new ApiException("Missing Password");
		}
		if(Valide.isValide(user.getEmail()))
		{
			return userRepository.save(user);
		}
		return null;
			
	}
}
