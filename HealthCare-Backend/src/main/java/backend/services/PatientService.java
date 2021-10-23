package backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Patient;
import backend.repository.PatientRepository;


@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public void registerPatient(Patient patient) {
		patientRepository.save(patient);
	}

	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

	public Patient getPatient(String id) {
		return patientRepository.findById(id).get();
	}

	public void deletePatient(String id) {
		patientRepository.deleteById(id);
	}

}
