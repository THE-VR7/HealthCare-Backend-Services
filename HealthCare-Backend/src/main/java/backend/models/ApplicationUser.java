package backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ApplicationUser {
	@Id
	@GeneratedValue
	public Long userid;
	public String user_name;
	public String user_email;
	public String password;
	public String user_mobile;
	public String location;

	public ApplicationUser(String user_name, String user_email, String password, String user_mobile, String location) {
		super();
		this.user_name = user_name;
		this.user_email = user_email;
		this.password = password;
		this.user_mobile = user_mobile;
		this.location = location;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ApplicationUser() {
		super();
	}

	public ApplicationUser(String user_name, String password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "ApplicationUser [userid=" + userid + ", user_name=" + user_name + ", user_email=" + user_email
				+ ", password=" + password + ", user_mobile=" + user_mobile + ", location=" + location + "]";
	}
	
	

	// public Date user_dob;

}
