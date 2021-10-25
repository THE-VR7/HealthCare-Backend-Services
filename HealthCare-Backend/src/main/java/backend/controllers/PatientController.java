package backend.controllers;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Patient;
import backend.services.PatientService;


@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	@SuppressWarnings("unchecked")
	@PostMapping("patients/register")
	public ResponseEntity<JSONObject> registerPatient(@RequestBody Patient patient) {
		JSONObject obj = new JSONObject();
		try {
			patientService.registerPatient(patient);
			obj.put("message", "Registration Successful");
			return new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
		} catch (Exception e) {
			obj.put("message", "Registration failure");
			return new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("patients/list/")
	public List<Patient> getPatients() {
		return patientService.getPatients();
	}

	@GetMapping("patients/view/{Id}")
	public Patient getPatient(@PathVariable String Id) {
		return patientService.getPatient(Id);
	}

	@DeleteMapping("patients/delete/{Id}")
	public void deletePatient(@PathVariable String Id) {
		patientService.deletePatient(Id);
	}

}
