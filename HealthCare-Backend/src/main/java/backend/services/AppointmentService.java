package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Appointment;
import backend.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	public void deleteAppointment(String appintId) {
		appointmentRepository.deleteById(appintId);
	}

	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	public void saveAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	public Appointment getAppointmentById(String appointmentId) {
		return appointmentRepository.findById(appointmentId).get();
	}

	public Appointment getAppointmentByPatientId(String patientid) {
		return appointmentRepository.findByPatientId(patientid);
	}

}
