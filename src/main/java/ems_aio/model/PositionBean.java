package ems_aio.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

public class PositionBean {
	private String id;
	@NotEmpty(message="Feild must not be null!")
	private String name;
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
