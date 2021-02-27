package ems_aio.model;

import java.sql.Timestamp;
import java.util.Date;

import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.StaffDto;

public class MovementBean {

	private String id;
	private StaffDto sid;
	private String name;
	private String prepos;
	private MPOS001 pos;
	private String posname;
	private MDEP001 predep;
	private MDEP001 dep;
	private String depname;
	private Double presalary;
	private Double salary;
	private String remark;
	private String process;
	private Timestamp create;
	private String search;
	
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
	public String getPrepos() {
		return prepos;
	}
	public void setPrepos(String prepos) {
		this.prepos = prepos;
	}
	public MPOS001 getPos() {
		return pos;
	}
	public void setPos(MPOS001 pos) {
		this.pos = pos;
	}
	public String getPosname() {
		return posname;
	}
	public void setPosname(String posname) {
		this.posname = posname;
	}
	public MDEP001 getPredep() {
		return predep;
	}
	public void setPredep(MDEP001 predep) {
		this.predep = predep;
	}
	public MDEP001 getDep() {
		return dep;
	}
	public void setDep(MDEP001 dep) {
		this.dep = dep;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public Timestamp getCreate() {
		return create;
	}
	public void setCreate(Timestamp create) {
		this.create = create;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Double getPresalary() {
		return presalary;
	}
	public void setPresalary(Double presalary) {
		this.presalary = presalary;
	}
	
}
