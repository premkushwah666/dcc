package com.dcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcc.StringUtil;
import com.dcc.Exception.ApiException;
import com.dcc.entity.User;
import com.dcc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/check")
	public String check()
	{
		return "OK";
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User user) throws ApiException
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
			userService.createUser(user);
			return new ResponseEntity<>(user,HttpStatus.CREATED);
		}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateUSer")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		return null;
	}
}
