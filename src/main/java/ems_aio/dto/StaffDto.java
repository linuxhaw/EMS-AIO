package ems_aio.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MEMP001")
public class StaffDto {
	@Id
	@Column(name="emp_id")
	private String id;
	@Column(name="emp_name")
	private String name;
	@Column(name="emp_nrc")
	private String nrc;
	@Column(name="emp_email")
	private String email;
	@Column(name="emp_address")
	private String address;
	@Column(name="emp_phone")
	private String phone;
	@Column(name="emp_birthday")
	private Date birthday;
	@Column(name="emp_gender")
	private String gender;
	@Column(name="emp_marrage")
	private String marrage;
	@Column(name="emp_religion")
	private String religion;
	@Column(name="emp_nationality")
	private String nation;
	@Column(name="emp_password")
	private String password;
	@Column(name="emp_payroll")
	private double payroll;
	@Column(name="emp_bnkacc")
	private String bnkacc;
	/*@OneToMany
	@JoinColumn(name="bnk_id")*/
	@Column(name="emp_bnk")
	private String bank;
	@Column(name="emp_register")
	private Date register;
	@Column(name="emp_pos")
	private String pos;
	@Column(name="emp_dep")
	private String dep;
	@Column(name="emp_rol")
	private String role;
	@Column(name="emp_create")
	private Timestamp createdate;
	@Column(name="emp_update")
	private Timestamp updatedate;
	@Column(name="emp_status")
	private boolean status;
	/*
	 * @OneToMany private List<MBNK001> mbnk001=new ArrayList<MBNK001>();
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMarrage() {
		return marrage;
	}
	public void setMarrage(String marrage) {
		this.marrage = marrage;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getPayroll() {
		return payroll;
	}
	public void setPayroll(double payroll) {
		this.payroll = payroll;
	}
	public String getBnkacc() {
		return bnkacc;
	}
	public void setBnkacc(String bnkacc) {
		this.bnkacc = bnkacc;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Date getRegister() {
		return register;
	}
	public void setRegister(Date register) {
		this.register = register;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
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
	
}
