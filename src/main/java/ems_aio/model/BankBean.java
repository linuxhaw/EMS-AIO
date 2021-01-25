package ems_aio.model;

import javax.validation.constraints.NotEmpty;

public class BankBean {
	private String id;
	@NotEmpty(message="Input Require")
	private String name;
	private String phone;
	private String loc;
	private String create;
	private String update;
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
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
