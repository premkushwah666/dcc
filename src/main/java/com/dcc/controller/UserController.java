package com.dcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/check")
	public String check()
	{
		return "OK";
	}
	
	@PutMapping("/updateUser/id/{myid}")
	public ResponseEntity<User> updateUser(@PathVariable Integer myid,@RequestBody User user) throws ApiException
	{
//		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
//		String name=auth.getName();
		//User userInDb=userService.findByUserName(name);
		//User userInDb=userService.findByEmail(user.getEmail());
		User userInDb=userService.findById(myid).orElse(null);
		//Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		//String name=auth.getName();
		//User userInDb=userService.findByEmail(user.getEmail());
		//User userInDb=userService.findByEmail(name);
		if(userInDb!=null&& userInDb.isActive())
		{
			userInDb.setUserName(user.getUserName()!=null?user.getUserName():userInDb.getUserName());
			userInDb.setPassword(user.getPassword()!=null?passwordEncoder.encode(user.getPassword()):userInDb.getPassword());
			userInDb.setEmail(user.getEmail()!=null?user.getEmail():userInDb.getEmail());
			userService.updateUser(userInDb);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		throw new ApiException("User Not Found/Activated");
	}
	
	@DeleteMapping("/deleteUser/id/{myid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer myid)
	{
//		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
//		String name=auth.getName();
		//User userInDb=userService.findByUserName(name);
		User userInDb=userService.findById(myid).orElse(null);
		//User userInDb=userService.findByEmail(name);
		if(userInDb != null && userInDb.isActive())
		{
			userInDb.setActive(false);
			userService.createUser(userInDb);
			return new ResponseEntity<>(new ApiResponse("User diactivated Successfully"),HttpStatus.OK);
		}
		throw new ApiException("User Not Found/Activated");
	}
}
