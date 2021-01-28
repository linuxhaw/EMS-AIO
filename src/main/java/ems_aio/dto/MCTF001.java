package ems_aio.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MCTF001 {
	@Id
	@Column(name="ctf_id")
	private String id;
	@Column(name="ctf_name")
	private String name;
	@Column(name="ctf_school")
	private String school;
	@Column(name="ctf_create")
	private Timestamp createdate;
	@Column(name="ctf_update")
	private Timestamp updatedate;
	@Column(name="ctf_status")
	private boolean status;
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
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public MCTF001(String id, String name, String school, Timestamp createdate, Timestamp updatedate, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.school = school;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.status = status;
	}
	public MCTF001() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
