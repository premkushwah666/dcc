package com.dcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcc.ApiResponse;
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
	
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws ApiException
	{
		//Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		//String name=auth.getName();
		User userInDb=userService.findByEmail(user.getEmail());
		//User userInDb=userService.findByEmail(name);
		if(userInDb!=null)
		{
			userInDb.setUserName(user.getUserName()!=null?user.getUserName():userInDb.getUserName());
			userInDb.setPassword(user.getPassword()!=null?user.getPassword():userInDb.getPassword());
			userInDb.setEmail(user.getEmail()!=null?user.getEmail():userInDb.getEmail());
			userService.createUser(userInDb);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		throw new ApiException("User Not Found");
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<ApiResponse> deleteUser()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		User userInDb=userService.findByUserName(name);
		//User userInDb=userService.findByEmail(name);
		if(userInDb!=null)
		{
			userService.deleteUser(name);
			return new ResponseEntity<>(new ApiResponse("User deleted Successfully"),HttpStatus.OK);
		}
		throw new ApiException("User Not Found");
	}
}
