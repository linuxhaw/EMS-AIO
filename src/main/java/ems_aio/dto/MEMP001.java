package ems_aio.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MEMP001 {

	@Id
	@Column (name="emp_id")
	private String empid;
	@Column (name="emp_name")
	private String empname;	
	@Column (name="emp_nrc")
	private String empnrc;
	@Column (name="emp_email")
	private String empemail;
	@Column (name="emp_address")
	private String empaddress;
	@Column (name="emp_phone")
	private String empphone;
	@Column (name="emp_birthday")
	private Date empbirthday;
	@Column (name="emp_gender")
	private String empgender;
	@Column (name="emp_marrage")
	private String empmarrage;
	@Column (name="emp_relgion")
	private String emprelgion;
	@Column (name="emp_natiuonality")
	private String empnatiuonality;
	@Column (name="emp_password")
	private String emp_password;
	@Column (name="emp_payroll")
	private Double emppayroll;
	@Column (name="emp_bankacc")
	private String empbankacc;
	@Column (name="emp_bank")
	private String empbank;
	@Column (name="emp_rol")
	private String emprol;
	@Column (name="emp_department")
	private String empdepartment;
	@Column (name="emp_position")
	private String empposition;
	@Column (name="emp_certification")
	private String empcertification;
	@Column (name="emp_qualification")
	private String empqualification;

	
	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpnrc() {
		return empnrc;
	}

	public void setEmpnrc(String empnrc) {
		this.empnrc = empnrc;
	}

	public String getEmpemail() {
		return empemail;
	}

	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}

	public String getEmpaddress() {
		return empaddress;
	}

	public void setEmpaddress(String empaddress) {
		this.empaddress = empaddress;
	}

	public String getEmpphone() {
		return empphone;
	}

	public void setEmpphone(String empphone) {
		this.empphone = empphone;
	}

	public Date getEmpbirthday() {
		return empbirthday;
	}

	public void setEmpbirthday(Date empbirthday) {
		this.empbirthday = empbirthday;
	}

	public String getEmpgender() {
		return empgender;
	}

	public void setEmpgender(String empgender) {
		this.empgender = empgender;
	}

	public String getEmpmarrage() {
		return empmarrage;
	}

	public void setEmpmarrage(String empmarrage) {
		this.empmarrage = empmarrage;
	}

	public String getEmprelgion() {
		return emprelgion;
	}

	public void setEmprelgion(String emprelgion) {
		this.emprelgion = emprelgion;
	}

	public String getEmpnatiuonality() {
		return empnatiuonality;
	}

	public void setEmpnatiuonality(String empnatiuonality) {
		this.empnatiuonality = empnatiuonality;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public Double getEmppayroll() {
		return emppayroll;
	}

	public void setEmppayroll(Double emppayroll) {
		this.emppayroll = emppayroll;
	}

	public String getEmpbankacc() {
		return empbankacc;
	}

	public void setEmpbankacc(String empbankacc) {
		this.empbankacc = empbankacc;
	}

	public String getEmpbank() {
		return empbank;
	}

	public void setEmpbank(String empbank) {
		this.empbank = empbank;
	}

	public String getEmpdepartment() {
		return empdepartment;
	}

	public void setEmpdepartment(String empdepartment) {
		this.empdepartment = empdepartment;
	}

	public String getEmpposition() {
		return empposition;
	}

	public void setEmpposition(String empposition) {
		this.empposition = empposition;
	}

	public String getEmpcertification() {
		return empcertification;
	}

	public void setEmpcertification(String empcertification) {
		this.empcertification = empcertification;
	}

	public String getEmpqualification() {
		return empqualification;
	}

	public void setEmpqualification(String empqualification) {
		this.empqualification = empqualification;
	}

	public String getEmprol() {
		return emprol;
	}

	public void setEmprol(String emprol) {
		this.emprol = emprol;
	}

	public MEMP001(String empid, String empname, String empnrc, String empemail, String empaddress, String empphone,
			Date empbirthday, String empgender, String empmarrage, String emprelgion, String empnatiuonality,
			String emp_password, Double emppayroll, String empbankacc, String empbank, String emprol,
			String empdepartment, String empposition, String empcertification, String empqualification) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.empnrc = empnrc;
		this.empemail = empemail;
		this.empaddress = empaddress;
		this.empphone = empphone;
		this.empbirthday = empbirthday;
		this.empgender = empgender;
		this.empmarrage = empmarrage;
		this.emprelgion = emprelgion;
		this.empnatiuonality = empnatiuonality;
		this.emp_password = emp_password;
		this.emppayroll = emppayroll;
		this.empbankacc = empbankacc;
		this.empbank = empbank;
		this.emprol = emprol;
		this.empdepartment = empdepartment;
		this.empposition = empposition;
		this.empcertification = empcertification;
		this.empqualification = empqualification;
	}
	
}
