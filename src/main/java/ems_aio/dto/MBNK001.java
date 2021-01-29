package ems_aio.dto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MBNK001 {
	@Id
	@Column(name="bnk_id")
	private String bnkid;
	@Column(name="bnk_name")
	private String bnkname;
	@Column(name="bnk_phone")
	private String bnkphone;
	@Column(name="bnk_loc")
	private String bnkloc;
	@Column(name="bnk_create")
	private Timestamp createdate;
	@Column(name="bnk_update")
	private Timestamp updatedate;
	@Column(name="bnk_status")
	private boolean status;
	public String getBnkid() {
		return bnkid;
	}
	public void setBnkid(String bnkid) {
		this.bnkid = bnkid;
	}
	public String getBnkname() {
		return bnkname;
	}
	public void setBnkname(String bnkname) {
		this.bnkname = bnkname;
	}
	public String getBnkphone() {
		return bnkphone;
	}
	public void setBnkphone(String bnkphone) {
		this.bnkphone = bnkphone;
	}
	public String getBnkloc() {
		return bnkloc;
	}
	public void setBnkloc(String bnkloc) {
		this.bnkloc = bnkloc;
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
	public MBNK001(String bnkid, String bnkname, String bnkphone, String bnkloc, Timestamp createdate,
			Timestamp updatedate, boolean status) {
		super();
		this.bnkid = bnkid;
		this.bnkname = bnkname;
		this.bnkphone = bnkphone;
		this.bnkloc = bnkloc;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.status = status;
	}
	public MBNK001() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
