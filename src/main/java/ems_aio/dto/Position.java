package ems_aio.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Position {
@Id
private String pId;
private String pName;
public String getpId() {
	return pId;
}
public void setpId(String pId) {
	this.pId = pId;
}
public String getpName() {
	return pName;
}
public void setpName(String pName) {
	this.pName = pName;
}
}
