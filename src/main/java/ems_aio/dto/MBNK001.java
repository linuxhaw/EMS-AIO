package ems_aio.dto;

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
	private String createdate;
	@Column(name="bnk_update")
	private String updatedate;
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

	public void setStatus(boolean status) {
		this.status = status;
	}

	public MBNK001(String bnkid, String bnkname, String bnkphone, String bnkloc, String createdate, String updatedate,
			boolean status) {
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
