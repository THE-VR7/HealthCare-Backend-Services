package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import backend.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

	@Query("Select a from Appointment a where a.patientId = ?1")
	public Appointment findByPatientId(String patientId);

}
