package ems_aio.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MCEF001 {

	@Id
	@Column (name="cef_id")
	private String cefid;
	@Column (name="cef_name")
	private String cefname;
	@Column (name="cef_schname")
	private String cefschname;
}
