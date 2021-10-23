package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import backend.models.ApplicationUser;
import backend.services.ApplicationUserService;

@RestController
public class ApplicationUserController {

	@Autowired
	private ApplicationUserService userService;

	@GetMapping("viewprofile/{userid}")
	public ApplicationUser getUser(@PathVariable int userid) {
		return userService.getUserById(userid);
	}

	@PutMapping("editprofile/{userid}")
	public ApplicationUser editUser(@PathVariable int userid) {
		return userService.editUserById(userid);
	}

	@PostMapping("register")
	public void registerUser(@RequestBody ApplicationUser user) {

	}

	@PostMapping("login")
	public void loginUser(@RequestBody ApplicationUser user) {

	}

}
