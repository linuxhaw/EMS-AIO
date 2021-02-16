package ems_aio.model;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import ems_aio.dto.StaffDto;

public class PosReportBean {
	private String id;
	private String name;
	private int total;
	List<StaffDto> list;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<StaffDto> getList() {
		return list;
	}
	public void setList(List<StaffDto> list) {
		this.list = list;
	}
	
	
}
