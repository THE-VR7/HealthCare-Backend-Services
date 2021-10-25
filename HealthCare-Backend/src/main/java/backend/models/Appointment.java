package backend.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String booking_id;
	private String disease;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date tentativeDate;
	private String priority;

	private String patientId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bookingTime;

	public Appointment(String disease, Date tentativeDate, String priority, String patientId) {
		super();

		this.disease = disease;
		this.tentativeDate = tentativeDate;
		this.priority = priority;
		this.patientId = patientId;

	}

	public Appointment() {
		super();
	}

	public String getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Date getTentativeDate() {
		return tentativeDate;
	}

	public void setTentativeDate(Date tentativeDate) {
		this.tentativeDate = tentativeDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}

}
