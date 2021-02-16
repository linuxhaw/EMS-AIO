package ems_aio.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMPSAL")
public class EmployeeSalaryDto {

	@Id
	@Column
	private String empsal_id;
	@ManyToOne
	private StaffDto emp_id;
	@ManyToOne
	private StaffDto emp_name;
	@ManyToOne
	private StaffDto emp_pos;
	@ManyToOne
	private StaffDto emp_dep;
	@Column
	private Double empsal_salary;
	@Column
	private Date empsal_date;
	@Column
	private Timestamp empsal_create;
	@Column
	private Timestamp empsal_update;
	public String getEmpsal_id() {
		return empsal_id;
	}
	public void setEmpsal_id(String empsal_id) {
		this.empsal_id = empsal_id;
	}
	public StaffDto getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(StaffDto emp_name) {
		this.emp_name = emp_name;
	}
	public StaffDto getEmp_pos() {
		return emp_pos;
	}
	public void setEmp_pos(StaffDto emp_pos) {
		this.emp_pos = emp_pos;
	}
	public StaffDto getEmp_dep() {
		return emp_dep;
	}
	public void setEmp_dep(StaffDto emp_dep) {
		this.emp_dep = emp_dep;
	}
	public Double getEmpsal_salary() {
		return empsal_salary;
	}
	public void setEmpsal_salary(Double empsal_salary) {
		this.empsal_salary = empsal_salary;
	}
	public Date getEmpsal_date() {
		return empsal_date;
	}
	public void setEmpsal_date(Date empsal_date) {
		this.empsal_date = empsal_date;
	}
	public Timestamp getEmpsal_create() {
		return empsal_create;
	}
	public void setEmpsal_create(Timestamp empsal_create) {
		this.empsal_create = empsal_create;
	}
	public Timestamp getEmpsal_update() {
		return empsal_update;
	}
	public void setEmpsal_update(Timestamp empsal_update) {
		this.empsal_update = empsal_update;
	}
	public StaffDto getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(StaffDto emp_id) {
		this.emp_id = emp_id;
	}
	
	
	
	
	
}
