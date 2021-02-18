package ems_aio.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

public class BankBean {
	private String id;
	private String name;
	@Digits( message="Mobile number is invalid", fraction = 0, integer = 11)
	private String phone;
	private String loc;
	private Timestamp create;
	private Timestamp update;
	private String status;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Timestamp getCreate() {
		return create;
	}
	public void setCreate(Timestamp create) {
		this.create = create;
	}
	public Timestamp getUpdate() {
		return update;
	}
	public void setUpdate(Timestamp update) {
		this.update = update;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	

}
