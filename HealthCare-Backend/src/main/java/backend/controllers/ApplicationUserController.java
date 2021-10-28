package backend.controllers;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import backend.models.ApplicationUser;
import backend.security.JwtUtil;
import backend.services.ApplicationUserService;
import backend.services.UserAuthService;
import io.swagger.models.HttpMethod;

@RestController
public class ApplicationUserController {

	@Autowired
	private ApplicationUserService userService;
	
	@Autowired
	private UserAuthService userAuthService;

	@GetMapping(value = "viewprofilebyId/{userid}", produces = "application/json")
	public ApplicationUser getUserById(@PathVariable int userid) {
		return userService.getUserById(userid);
	}
	
	@GetMapping(value = "viewprofile/{username}", produces = "application/json")
	public ApplicationUser getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	@PutMapping("editprofile/{userid}")
	public ApplicationUser editUser(@PathVariable int userid) {
		return userService.editUserById(userid);
	}

	@RequestMapping(value = "register", produces = "application/json", method = RequestMethod.POST )
	public ResponseEntity<JSONObject> registerUser(@RequestBody ApplicationUser user) {
		JSONObject obj = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			userService.registerUser(user);
			obj.put("message", "Registration Successful");
			return new ResponseEntity<JSONObject>(obj, headers, HttpStatus.OK);
		} catch (Exception e) {
			obj.put("message", "Password or username policy failed");
			return new ResponseEntity<JSONObject>(obj, headers, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "signin", produces = "application/json")
	public ResponseEntity<JSONObject> loginUser(@RequestBody ApplicationUser user) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject obj = new JSONObject();
		try {
			obj = userAuthService.loginUser(user);
			return new ResponseEntity<JSONObject>(obj, headers, HttpStatus.OK);
		} catch (Exception e) {
			obj.put("message", "Password or username policy failed");
			return new ResponseEntity<JSONObject>(obj, headers, HttpStatus.BAD_REQUEST);
		}
	}

}
