<<<<<<< HEAD
package ems_aio.model;

public class PositionBean {
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
=======
package ems_aio.model;

import javax.validation.constraints.NotEmpty;

public class PositionBean {
	private String id;
	@NotEmpty(message="Input Require")
	private String name;
	private String create;
	private String update;
	private String status;
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
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
>>>>>>> 657aeebe99d3ab8c3ac0d40650c70a579fa2e4a0
