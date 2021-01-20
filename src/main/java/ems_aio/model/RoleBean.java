package ems_aio.model;

import javax.validation.constraints.NotEmpty;

public class RoleBean {
	@NotEmpty(message="Id can't be empty")
	private String id;
	@NotEmpty(message="Password can't be empty")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
