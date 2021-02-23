package ems_aio.dto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMPSAL")
public class EmpSalDto {

	@Id
	@Column
	private String sal_id;
	@ManyToOne
	private StaffDto sal_empid;
	@ManyToOne
	private MPOS001 sal_pos;
	@ManyToOne
	private MDEP001 sal_dep;
	@Column
	private Double sal_salary;
	@ManyToOne
	private StaffDto sal_admin;
	@Column
	private Date sal_date;
	@Column
	private Double sal_allowance;
	@Column
	private Double sal_deduction;
	@Column
	private Double sal_total;
	@Column
	private Timestamp sal_create;
	public String getSal_id() {
		return sal_id;
	}
	public void setSal_id(String sal_id) {
		this.sal_id = sal_id;
	}
	public StaffDto getSal_empid() {
		return sal_empid;
	}
	public void setSal_empid(StaffDto sal_empid) {
		this.sal_empid = sal_empid;
	}
	public MPOS001 getSal_pos() {
		return sal_pos;
	}
	public void setSal_pos(MPOS001 sal_pos) {
		this.sal_pos = sal_pos;
	}
	public MDEP001 getSal_dep() {
		return sal_dep;
	}
	public void setSal_dep(MDEP001 sal_dep) {
		this.sal_dep = sal_dep;
	}
	public Double getSal_salary() {
		return sal_salary;
	}
	public void setSal_salary(Double sal_salary) {
		this.sal_salary = sal_salary;
	}
	public StaffDto getSal_admin() {
		return sal_admin;
	}
	public void setSal_admin(StaffDto sal_admin) {
		this.sal_admin = sal_admin;
	}
	public Date getSal_date() {
		return sal_date;
	}
	public void setSal_date(Date sal_date) {
		this.sal_date = sal_date;
	}
	public Timestamp getSal_create() {
		return sal_create;
	}
	public void setSal_create(Timestamp sal_create) {
		this.sal_create = sal_create;
	}

	public Double getSal_allowance() {
		return sal_allowance;
	}
	public void setSal_allowance(Double sal_allowance) {
		this.sal_allowance = sal_allowance;
	}
	public Double getSal_deduction() {
		return sal_deduction;
	}
	public void setSal_deduction(Double sal_deduction) {
		this.sal_deduction = sal_deduction;
	}
	public Double getSal_total() {
		return sal_total;
	}
	public void setSal_total(Double sal_total) {
		this.sal_total = sal_total;
	}
	public EmpSalDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmpSalDto(String sal_id, StaffDto sal_empid, MPOS001 sal_pos, MDEP001 sal_dep, Double sal_salary,
			StaffDto sal_admin, Date sal_date, Double sal_allowance, Double sal_deduction, Double sal_total,
			Timestamp sal_create) {
		super();
		this.sal_id = sal_id;
		this.sal_empid = sal_empid;
		this.sal_pos = sal_pos;
		this.sal_dep = sal_dep;
		this.sal_salary = sal_salary;
		this.sal_admin = sal_admin;
		this.sal_date = sal_date;
		this.sal_allowance = sal_allowance;
		this.sal_deduction = sal_deduction;
		this.sal_total = sal_total;
		this.sal_create = sal_create;
	}
	
	
	
}
