package ems_aio.dto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMPMOV")
public class EmpMovDto {

	@Id
	@Column
	private String mov_id;
	@ManyToOne
	private StaffDto mov_empid;
	@ManyToOne
	private MPOS001 mov_pos;
	@ManyToOne
	private MDEP001 mov_dep;
	@Column
	private String mov_process;
	@Column
	private Date mov_start;
	@Column
	private Date mov_end;
	@Column
	private String mov_remark;
	@Column
	private Timestamp mov_create;
	public String getMov_id() {
		return mov_id;
	}
	public void setMov_id(String mov_id) {
		this.mov_id = mov_id;
	}
	public StaffDto getMov_empid() {
		return mov_empid;
	}
	public void setMov_empid(StaffDto mov_empid) {
		this.mov_empid = mov_empid;
	}
	public MPOS001 getMov_pos() {
		return mov_pos;
	}
	public void setMov_pos(MPOS001 mov_pos) {
		this.mov_pos = mov_pos;
	}
	public MDEP001 getMov_dep() {
		return mov_dep;
	}
	public void setMov_dep(MDEP001 mov_dep) {
		this.mov_dep = mov_dep;
	}
	public String getMov_remark() {
		return mov_remark;
	}
	public void setMov_remark(String mov_remark) {
		this.mov_remark = mov_remark;
	}
	public Timestamp getMov_create() {
		return mov_create;
	}
	public void setMov_create(Timestamp mov_create) {
		this.mov_create = mov_create;
	}
	public String getMov_process() {
		return mov_process;
	}
	public void setMov_process(String mov_process) {
		this.mov_process = mov_process;
	}
	
	public Date getMov_start() {
		return mov_start;
	}
	public void setMov_start(Date mov_start) {
		this.mov_start = mov_start;
	}
	public Date getMov_end() {
		return mov_end;
	}
	public void setMov_end(Date mov_end) {
		this.mov_end = mov_end;
	}

	
	public EmpMovDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmpMovDto(String mov_id, StaffDto mov_empid, MPOS001 mov_pos, MDEP001 mov_dep, String mov_process,
			Date mov_start, Date mov_end, String mov_remark, Timestamp mov_create) {
		super();
		this.mov_id = mov_id;
		this.mov_empid = mov_empid;
		this.mov_pos = mov_pos;
		this.mov_dep = mov_dep;
		this.mov_process = mov_process;
		this.mov_start = mov_start;
		this.mov_end = mov_end;
		this.mov_remark = mov_remark;
		this.mov_create = mov_create;
	}


	
	
}
