package com.dcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.dcc.Validation.Valide;
import com.dcc.entity.User;
import com.dcc.service.UserService;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List> getAllUsers()
	{
		List allUsers=userService.getAllUsers();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User user) throws ApiException
	{
			if(Valide.isValideEmail(user.getEmail()))
			{
				userService.createUserByAdmin(user);
				return new ResponseEntity<>(user,HttpStatus.CREATED);
			}
			else
			{
				throw new ApiException("Invalide Email");
			}
	}


	@PutMapping("/updateUser/id/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Integer userId,@RequestBody User user) throws ApiException
	{
		User userInDb=userService.findById(userId).orElse(null);
		if(userInDb!=null&& userInDb.isActive())
		{
			userInDb.setUserName(user.getUserName()!=null?user.getUserName():userInDb.getUserName());
			userInDb.setPassword(user.getPassword()!=null?passwordEncoder.encode(user.getPassword()):userInDb.getPassword());
			userInDb.setEmail(user.getEmail()!=null?user.getEmail():userInDb.getEmail());
			userInDb.setRole(user.getRole()!=null?user.getRole():userInDb.getRole());
			userService.updateUser(userInDb);
			return new ResponseEntity<>(new ApiResponse("User updated successfully"),HttpStatus.OK);
		}
		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		throw new ApiException("User Not Found/Activated");
	}
	
	@DeleteMapping("/deleteUser/id/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
//		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
//		String name=auth.getName();
		User userInDb=userService.findById(userId).orElse(null);
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
