package ems_aio.model;

import javax.validation.constraints.NotEmpty;

public class UserBean {
	@NotEmpty(message="Id must not be empty!")
	private String id;
	@NotEmpty(message="Password must not be empty!")
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}






