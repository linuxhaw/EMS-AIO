package ems_aio.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MPOS001 {
	@Id
	@Column(name="pos_id")
	private String posid;
	@Column(name="pos_name")
	private String posname;
	@Column(name="pos_create")
	private Timestamp createdate;
	@Column(name="pos_update")
	private Timestamp updatedate;
	@Column(name="pos_status")
	private boolean status;
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getPosname() {
		return posname;
	}
	public void setPosname(String posname) {
		this.posname = posname;
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
	public MPOS001(String posid, String posname, Timestamp createdate, Timestamp updatedate, boolean status) {
		super();
		this.posid = posid;
		this.posname = posname;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.status = status;
	}
	public MPOS001() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
}
