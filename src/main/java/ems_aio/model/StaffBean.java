package ems_aio.model;

import java.sql.Timestamp;
import java.util.*;

import ems_aio.dto.MBNK001;
import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.MROL001;

public class StaffBean {

	private String id;
	private String name;
	private String password;
	private String nrc;
	private String email;
	private String phone;
	private double salary;
	private String bankAcc;
	private MBNK001 bank;
	private String address;
	private Date register;
	private String birthday;
	private String gender;
	private String marrage;
	private String religion;
	private String nation;
	private MPOS001 position;
	private MDEP001 department;
	private MROL001 role;
	private String status;
	private Timestamp create;
	private Timestamp update;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getBankAcc() {
		return bankAcc;
	}
	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}
	public MBNK001 getBank() {
		return bank;
	}
	public void setBank(MBNK001 bank) {
		this.bank = bank;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegister() {
		return register;
	}
	public void setRegister(Date register) {
		this.register = register;
	}
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
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
	public MPOS001 getPosition() {
		return position;
	}
	public void setPosition(MPOS001 position) {
		this.position = position;
	}
	public MDEP001 getDepartment() {
		return department;
	}
	public void setDepartment(MDEP001 department) {
		this.department = department;
	}
	public MROL001 getRole() {
		return role;
	}
	public void setRole(MROL001 role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreate() {
		return create;
	}
	public void setCreate(Timestamp create) {
		this.create = create;
	}
	public Timestamp getUpdate() {
		return update;
	}
	public void setUpdate(Timestamp update) {
		this.update = update;
	}
	

	
}
