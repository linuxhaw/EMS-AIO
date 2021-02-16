package ems_aio.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MEMP001")
public class StaffDto {
	@Id
	private String emp_id;
	
	private String emp_name;
	
	private String emp_nrc;
	
	private String emp_email;
	
	private String emp_address;
	
	private String emp_phone;
	
	private Date emp_birthday;
	
	private String emp_gender;
	
	private String emp_marrage;
	
	private String emp_religion;
	
	private String emp_nationality;
	
	private String emp_password;
	
	private double emp_payroll;
	
	private String emp_bnkacc;
	@ManyToOne
	@JoinColumn
	private MBNK001 emp_bnk;
	
	private Date emp_register;
	@ManyToOne
	@JoinColumn
	private MPOS001 emp_pos;
	@ManyToOne
	@JoinColumn
	private MDEP001 emp_dep;
	@ManyToOne
	@JoinColumn
	private MROL001 emp_rol;
	
	private Timestamp emp_create;
	
	private Timestamp emp_update;
	
	private boolean emp_status;
	
	private boolean emp_blacklist;
	
	@ManyToMany
	private Set<MCTF001> ctf = new HashSet<>();
	
	@ManyToMany
	private Set<MQUL001> qul = new HashSet<>();

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_nrc() {
		return emp_nrc;
	}

	public void setEmp_nrc(String emp_nrc) {
		this.emp_nrc = emp_nrc;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getEmp_address() {
		return emp_address;
	}

	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public Date getEmp_birthday() {
		return emp_birthday;
	}

	public void setEmp_birthday(Date emp_birthday) {
		this.emp_birthday = emp_birthday;
	}

	public String getEmp_gender() {
		return emp_gender;
	}

	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}

	public String getEmp_marrage() {
		return emp_marrage;
	}

	public void setEmp_marrage(String emp_marrage) {
		this.emp_marrage = emp_marrage;
	}

	public String getEmp_religion() {
		return emp_religion;
	}

	public void setEmp_religion(String emp_religion) {
		this.emp_religion = emp_religion;
	}

	public String getEmp_nationality() {
		return emp_nationality;
	}

	public void setEmp_nationality(String emp_nationality) {
		this.emp_nationality = emp_nationality;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public double getEmp_payroll() {
		return emp_payroll;
	}

	public void setEmp_payroll(double emp_payroll) {
		this.emp_payroll = emp_payroll;
	}

	public String getEmp_bnkacc() {
		return emp_bnkacc;
	}

	public void setEmp_bnkacc(String emp_bnkacc) {
		this.emp_bnkacc = emp_bnkacc;
	}

	public MBNK001 getEmp_bnk() {
		return emp_bnk;
	}

	public void setEmp_bnk(MBNK001 emp_bnk) {
		this.emp_bnk = emp_bnk;
	}

	public Date getEmp_register() {
		return emp_register;
	}

	public void setEmp_register(Date emp_register) {
		this.emp_register = emp_register;
	}

	public MPOS001 getEmp_pos() {
		return emp_pos;
	}

	public void setEmp_pos(MPOS001 emp_pos) {
		this.emp_pos = emp_pos;
	}

	public MDEP001 getEmp_dep() {
		return emp_dep;
	}

	public void setEmp_dep(MDEP001 emp_dep) {
		this.emp_dep = emp_dep;
	}

	public MROL001 getEmp_rol() {
		return emp_rol;
	}

	public void setEmp_rol(MROL001 emp_rol) {
		this.emp_rol = emp_rol;
	}

	public Timestamp getEmp_create() {
		return emp_create;
	}

	public void setEmp_create(Timestamp emp_create) {
		this.emp_create = emp_create;
	}

	public Timestamp getEmp_update() {
		return emp_update;
	}

	public void setEmp_update(Timestamp emp_update) {
		this.emp_update = emp_update;
	}

	public boolean isEmp_status() {
		return emp_status;
	}

	public void setEmp_status(boolean emp_status) {
		this.emp_status = emp_status;
	}

	public boolean isEmp_blacklist() {
		return emp_blacklist;
	}

	public void setEmp_blacklist(boolean emp_blacklist) {
		this.emp_blacklist = emp_blacklist;
	}

	public Set<MCTF001> getCtf() {
		return ctf;
	}

	public void setCtf(Set<MCTF001> ctf) {
		this.ctf = ctf;
	}

	public Set<MQUL001> getQul() {
		return qul;
	}

	public void setQul(Set<MQUL001> qul) {
		this.qul = qul;
	}

	public StaffDto(String emp_id, String emp_name, String emp_nrc, String emp_email, String emp_address,
			String emp_phone, Date emp_birthday, String emp_gender, String emp_marrage, String emp_religion,
			String emp_nationality, String emp_password, double emp_payroll, String emp_bnkacc, MBNK001 emp_bnk,
			Date emp_register, MPOS001 emp_pos, MDEP001 emp_dep, MROL001 emp_rol, Timestamp emp_create,
			Timestamp emp_update, boolean emp_status, boolean emp_blacklist, Set<MCTF001> ctf, Set<MQUL001> qul) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_nrc = emp_nrc;
		this.emp_email = emp_email;
		this.emp_address = emp_address;
		this.emp_phone = emp_phone;
		this.emp_birthday = emp_birthday;
		this.emp_gender = emp_gender;
		this.emp_marrage = emp_marrage;
		this.emp_religion = emp_religion;
		this.emp_nationality = emp_nationality;
		this.emp_password = emp_password;
		this.emp_payroll = emp_payroll;
		this.emp_bnkacc = emp_bnkacc;
		this.emp_bnk = emp_bnk;
		this.emp_register = emp_register;
		this.emp_pos = emp_pos;
		this.emp_dep = emp_dep;
		this.emp_rol = emp_rol;
		this.emp_create = emp_create;
		this.emp_update = emp_update;
		this.emp_status = emp_status;
		this.emp_blacklist = emp_blacklist;
		this.ctf = ctf;
		this.qul = qul;
	}

	public StaffDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
}