package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.LoginRequestDto;
import com.app.dto.UserDto;
import com.app.pojos.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;

	

	@PostMapping
	public User saveEmpDetails(@RequestBody UserDto transientUser) {
		return userService.add(transientUser);

	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteEmpDetails(@PathVariable Long id) {
	
		return new ApiResponse(userService.delete(id));
	}

	@GetMapping("/{id}")
	public User getEmpDetails(@PathVariable Long id) {
		return userService.fetchById(id);
	}

	@PutMapping
	public User updateEmpDetails(@RequestBody User detachedUser) {
		return userService.update(detachedUser);
	}
	
	@PostMapping("/signin")
	public User validateUser(@RequestBody LoginRequestDto dto)
	{
		System.out.println("in signin "+dto);
		return userService.authenticateEmp(dto);
	}

}
