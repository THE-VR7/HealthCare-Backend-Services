package backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.ApplicationUser;
import backend.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {

	@Autowired
	private ApplicationUserRepository userRepository;

	public ApplicationUser getUserById(long userid) {
		return userRepository.findById(userid).get();
	}

	public ApplicationUser editUserById(long userid) {
		return userRepository.findById(userid).get();
	}

}
