package backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.models.ApplicationUser;
import backend.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {

	@Autowired
	private ApplicationUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	public ApplicationUser getUserById(long userid) {
		return userRepository.findById(userid).get();
	}
	
	public ApplicationUser getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public ApplicationUser editUserById(long userid) {
		return userRepository.findById(userid).get();
	}

	public void registerUser(ApplicationUser user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
