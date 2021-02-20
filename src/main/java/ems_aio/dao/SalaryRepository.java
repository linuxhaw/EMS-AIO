package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ems_aio.dto.EmpSalDto;
import ems_aio.dto.MROL001;


public interface SalaryRepository extends CrudRepository<EmpSalDto,String>{
	@Query(value = "SELECT * FROM empsal ORDER BY sal_create DESC LIMIT 1;", nativeQuery = true)
	EmpSalDto findLastID();

	@Query(value = "SELECT * FROM empsal;", nativeQuery = true)
	List<EmpSalDto> getvalid();

	@Query(value = "SELECT * FROM empsal n WHERE (n.sal_id =?1 OR n.sal_empid= ?1)", nativeQuery = true)
	List<EmpSalDto> find(String cname);

	@Query(value = "SELECT * FROM empsal n WHERE (n.sal_id =?1 OR n.sal_empid= ?1)  ORDER BY  n.sal_date DESC", nativeQuery = true)
	  Page<EmpSalDto> findSearchPagi(String cname,Pageable pageable);
	
	@Query(value = "SELECT * FROM empsal n ORDER BY  n.sal_date DESC", nativeQuery = true)
	  Page<EmpSalDto> findPagi(Pageable pageable);
	//zay 19
	@Query(value = "SELECT * FROM empsal n WHERE (n.sal_empid_emp_id =?1 )  ORDER BY  n.sal_date DESC", nativeQuery = true)
	List<EmpSalDto> stffsal(String id);
}
