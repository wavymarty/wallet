package edu.itstep.blockchain.controller;

import edu.itstep.blockchain.domain.User;
import edu.itstep.blockchain.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("user")


public class UserController {
	@Autowired
	UserServiceImpl userService;
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/test_access")
	public String greeting(Authentication authentication) {

		String userName = authentication.getName();

		return "Spring Security In-memory Authentication Example - Welcome " + userName;
	}

	@PostMapping(value = "/createUser", consumes = "application/json", produces = "application/json")
	public User createUser(@RequestBody User user) {
		return userService.saveUpdateUser(user);
	}

	@Secured({ "ROLE_ROOT","ROLE_ADMIN"})
	@GetMapping("/users")
	
	public ResponseEntity<List<User>> getAllUsers() {
		
		return
				 ResponseEntity.ok().body(userService.getAllUsers());
				
	}

	@PostMapping(value = "/updateUser", consumes = "application/json", produces = "application/json")
	public User updateUser(@RequestBody User user, HttpServletResponse response) {
		response.setHeader("Location",
				ServletUriComponentsBuilder.fromCurrentContextPath().path("/findPerson/" + user.getId()).toUriString());

		return userService.saveUpdateUser(user);
	}
}
