package ems_aio.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MEMP001 {
	@Id
	@Column(name="rol_id")
	private String rolid;
	@Column(name="rol_name")
	private String rolname;
	@Column(name="rol_create")
	private String createdate;
	@Column(name="rol_update")
	private String updatedate;
	@Column(name="rol_status")
	private boolean status;

}
