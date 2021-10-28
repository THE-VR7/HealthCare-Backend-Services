package backend.services;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backend.models.ApplicationUser;
import backend.repository.ApplicationUserRepository;
import backend.security.ApiSecurityConfig;
import backend.security.JwtUtil;

@Service
public class UserAuthService implements UserDetailsService {

	private final Log logger = LogFactory.getLog(UserAuthService.class);
	
	@Autowired
	private ApplicationUserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ApplicationUser user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username : " + username);
		}

		return new User(user.getUser_name(), user.getPassword(), new ArrayList<>());
	}

	public JSONObject loginUser(ApplicationUser user) throws Exception {
		logger.info("in the loginuser method");
		authenticate(user.getUser_name(), user.getPassword());
		logger.info("argument user is " + user);
		ApplicationUser currUser = userRepository.findByUsername(user.getUser_name());
		logger.info("currUser is \" + currUser");
		final UserDetails userDetails = new User(currUser.getUser_name(), currUser.getPassword(), new ArrayList<>());
		logger.info("userDetails are " + userDetails.getPassword());
		final String token = jwtUtil.generateToken(userDetails);
		logger.info("Token is " + token);
		JSONObject obj = new JSONObject();
		obj.put("token", token);
		obj.put("message", "Authentication successful!");
		obj.put("id", currUser.getUserid());
		return obj;
	}

	private void authenticate(String username, String password) throws Exception {
		logger.info("username is " + username + " password is " + password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			logger.info("executed disabled exception");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			logger.info("executed bad credential exception" + e);
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}