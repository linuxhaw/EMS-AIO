package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ems_aio.dto.EmployeeSalaryDto;


public interface SalaryRepository extends CrudRepository<EmployeeSalaryDto,String>{
	@Query(value = "SELECT * FROM EMPSAL ORDER BY EMPSAL_CREATE DESC LIMIT 1;", nativeQuery = true)
	EmployeeSalaryDto findLastID();

	@Query(value = "SELECT * FROM EMPSAL;", nativeQuery = true)
	List<EmployeeSalaryDto> getvalid();

	@Query(value = "SELECT * FROM EMPSAL n WHERE (n.EMPSAL_ID =?1 OR n.EMPSAL_EMP = ?1) ", nativeQuery = true)
	List<EmployeeSalaryDto> find(String cname);

}
