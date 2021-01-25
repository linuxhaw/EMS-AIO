package ems_aio.dto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class MEMP001 {
	@Id
	@Column(name="emp_id")
	private String id;
	@Column(name="emp_name")
	private String name;
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
	
	/*@ManyToOne
	@JoinColumn(name="bnk_id")*/
	@Column(name="emp_bnk")
	private String bank;
	@Column(name="emp_pos")
	private String pos;
	@Column(name="emp_dep")
	private String emp_dep;
	@Column(name="emp_rol")
	private String emp_rol;
	@Column(name="dep_create")
	private Timestamp createdate;
	@Column(name="rol_update")
	private Timestamp updatedate;
	@Column(name="rol_status")
	private boolean status;

	
}
