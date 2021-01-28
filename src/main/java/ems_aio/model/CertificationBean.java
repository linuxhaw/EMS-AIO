package ems_aio.model;

import javax.validation.constraints.NotEmpty;

public class CertificationBean {

	private String id;
	@NotEmpty(message="Field must not be null!")
	private String cefname;
	@NotEmpty(message="Field must not be null!")
	private String schname;
	private String create;
	private String update;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCefname() {
		return cefname;
	}
	public void setCefname(String cefname) {
		this.cefname = cefname;
	}
	public String getSchname() {
		return schname;
	}
	public void setSchname(String schname) {
		this.schname = schname;
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
