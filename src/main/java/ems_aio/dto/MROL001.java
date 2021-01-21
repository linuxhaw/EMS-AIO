package ems_aio.dto;

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
	private String createdate;
	@Column(name="rol_update")
	private String updatedate;
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
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean b) {
		this.status = b;
	}
	
	
	public MROL001() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MROL001(String rolid, String rolname, String createdate, String updatedate, boolean status) {
		super();
		this.rolid = rolid;
		this.rolname = rolname;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.status = status;
	}
	
	
}
