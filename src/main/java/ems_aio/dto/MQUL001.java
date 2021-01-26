package ems_aio.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MQUL001 {
	@Id
	@Column(name="qul_id")
	private String qulid;
	@Column(name="qul_name")
	private String qulname;
	@Column(name="qul_school")
	private String qulschool;
	@Column(name="qul_create")
	private String createdate;
	@Column(name="qul_update")
	private String updatedate;
	@Column(name="qul_status")
	private boolean status;
	public String getQulid() {
		return qulid;
	}
	public void setQulid(String qulid) {
		this.qulid = qulid;
	}
	public String getQulname() {
		return qulname;
	}
	public void setQulname(String qulname) {
		this.qulname = qulname;
	}
	public String getQulschool() {
		return qulschool;
	}
	public void setQulschool(String qulschool) {
		this.qulschool = qulschool;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public MQUL001(String qulid, String qulname, String qulschool, String createdate, String updatedate,
			boolean status) {
		super();
		this.qulid = qulid;
		this.qulname = qulname;
		this.qulschool = qulschool;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.status = status;
	}
	public MQUL001() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
