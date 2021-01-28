package ems_aio.model;

import javax.validation.constraints.NotEmpty;

public class DepartmentBean {
	private String id;
	@NotEmpty(message="Field must not be null!")
	private String name;
	@NotEmpty(message="Field must not be null!")
	private String loc;
	@NotEmpty(message="Field must not be null!")
	private String head;
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
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
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
