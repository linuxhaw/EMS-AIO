package ems_aio.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MROL001 {
	@Id
	@Column(name="rol_id")
	private String rolid;
	@Column(name="rol_name")
	private String rolname;
	@Column(name="rol_create")
	private Timestamp createdate;
	@Column(name="rol_update")
	private Timestamp updatedate;
	@Column(name="rol_status")
	private boolean status;
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
	public MROL001(String rolid, String rolname, Timestamp createdate, Timestamp updatedate, boolean status) {
		super();
		this.rolid = rolid;
		this.rolname = rolname;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.status = status;
	}
	public MROL001() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
