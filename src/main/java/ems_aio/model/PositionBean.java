package ems_aio.model;

import javax.persistence.Column;

import javax.persistence.Id;




public class PositionBean {
	@Id
	@Column(name="pos_id")
	private String posid;
	@Column(name="pos_name")
	private String posname;
	@Column(name="pos_create")
	private String createdate;
	@Column(name="pos_update")
	private String updatedate;
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
	public PositionBean(String posid, String posname, String createdate, String updatedate, boolean status) {
		
		this.posid = posid;
		this.posname = posname;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.status = status;
	}
	public PositionBean() {
		
		// TODO Auto-generated constructor stub
	}
	
	
}
