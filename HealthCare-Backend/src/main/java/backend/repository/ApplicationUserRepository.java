package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backend.models.ApplicationUser;
import backend.models.Appointment;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	
	@Query("Select a from ApplicationUser a where a.user_name IN ?1")   
	public ApplicationUser findByUsername(String username);

	
}
