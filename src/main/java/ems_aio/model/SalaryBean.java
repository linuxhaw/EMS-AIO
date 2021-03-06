package ems_aio.model;

import java.sql.Timestamp;
import java.util.Date;

import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.StaffDto;

public class SalaryBean {

	private String id;
	private StaffDto sid;
	private String name;
	private MPOS001 salpos;
	private String salposname;
	private MDEP001 saldep;
	private String saldepname;
	private Double salary;
	private String saldate;
	private Double allowance;
	private Double deduction;
	private Double total;
	private Timestamp create;
	private String fromDate;
	private String toDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public StaffDto getSid() {
		return sid;
	}
	public void setSid(StaffDto sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MPOS001 getSalpos() {
		return salpos;
	}
	public void setSalpos(MPOS001 salpos) {
		this.salpos = salpos;
	}
	public String getSalposname() {
		return salposname;
	}
	public void setSalposname(String salposname) {
		this.salposname = salposname;
	}
	public MDEP001 getSaldep() {
		return saldep;
	}
	public void setSaldep(MDEP001 saldep) {
		this.saldep = saldep;
	}
	public String getSaldepname() {
		return saldepname;
	}
	public void setSaldepname(String saldepname) {
		this.saldepname = saldepname;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getSaldate() {
		return saldate;
	}
	public void setSaldate(String saldate) {
		this.saldate = saldate;
	}
	public Timestamp getCreate() {
		return create;
	}
	public void setCreate(Timestamp create) {
		this.create = create;
	}
	public Double getAllowance() {
		return allowance;
	}
	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}
	public Double getDeduction() {
		return deduction;
	}
	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	
	
}
