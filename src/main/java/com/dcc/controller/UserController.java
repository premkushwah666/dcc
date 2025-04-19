package com.dcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcc.ApiResponse;
import com.dcc.Exception.ApiException;
import com.dcc.Validation.Valide;
import com.dcc.entity.User;
import com.dcc.service.UserService;
import com.dcc.util.StringUtil;

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
			if(Valide.isValide(user.getEmail()))
			{
				userService.createUser(user);
				return new ResponseEntity<>(user,HttpStatus.CREATED);
			}
			else
			{
				throw new ApiException("Invalide Email");
			}
		}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws ApiException
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		User userInDb=userService.findByUserName(name);
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
		if(userInDb != null && userInDb.isActive())
		{
			userInDb.setActive(false);
			userService.createUser(userInDb);
			return new ResponseEntity<>(new ApiResponse("User diactivated Successfully"),HttpStatus.OK);
		}
		throw new ApiException("User Not Activated");
	}
}
