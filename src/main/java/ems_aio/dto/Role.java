package ems_aio.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Role {
	@Id
	@Column(name="rol_id")
	private String rolid;
	@Column(name="rol_name")
	private String rolname;
	@Column(name="rol_create")
	private String createdate;
	@Column(name="rol_update")
	private String updatedate;
	@Column(name="rol_status")
	private String status;
	

	public String getRolid() {
		return rolid;
	}
	public void setRolid(String rolid) {
		this.rolid = rolid;
	}
	public String getRolname() {
		return rolname;
	}
	public void setRolname(String rolname) {
		this.rolname = rolname;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(String rolid, String rolname, String createdate, String updatedate, String status) {
		super();
		this.rolid = rolid;
		this.rolname = rolname;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.status = status;
	}
	
	
	/*
	 * public Role(String studentId, String studentName, String registerDate, String
	 * status, String className) { super(); this.studentId = studentId;
	 * this.studentName = studentName; this.registerDate = registerDate; this.status
	 * = status; this.className = className; } public Role() { super(); }
	 */
}
