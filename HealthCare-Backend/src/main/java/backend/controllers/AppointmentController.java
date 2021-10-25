package backend.controllers;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.models.Appointment;
import backend.services.AppointmentService;


@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	@SuppressWarnings("unchecked")
	@PostMapping("appointment/register")
	public ResponseEntity<JSONObject> registerAppointment(@RequestBody Appointment appointment) {
		JSONObject obj = new JSONObject();
		try {
			service.saveAppointment(appointment);
			obj.put("message", "Booking Successful");
			return new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
		} catch (Exception e) {
			obj.put("message", "Booking failure");
			return new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("appointment/list")
	public List<Appointment> getAppointments() {
		return service.getAllAppointments();
	}

	@GetMapping("appointment/view/{appointmentId}")
	public Appointment getAppointmentById(@PathVariable String appointmentId) {
		return service.getAppointmentById(appointmentId);
	}

	@GetMapping("appointment/list/{patientid}")
	public List<Appointment> getAppointmentByPatientId(@PathVariable String patientid) {
		return service.getAppointmentByPatientId(patientid);
	}

	@DeleteMapping("appointment/delete/{appointmentId}")
	public void deleteAppointmentById(@PathVariable String appointmentId) {
		service.deleteAppointment(appointmentId);
	}

}
