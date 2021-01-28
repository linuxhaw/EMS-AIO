package ems_aio.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
@Entity
public class User {
	@Id
	@NotEmpty(message="Id must not be empty!")
	private String id;
	@NotEmpty(message="Name must not be empty!")
	private String name;
	@NotEmpty(message="Password must not be empty!")
	private String password;
	private String role;
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User() {
		super();
	}
		
	public User(@NotEmpty String id,@NotEmpty String name,@NotEmpty String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
