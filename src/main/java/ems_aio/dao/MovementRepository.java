package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ems_aio.dto.EmpMovDto;



public interface MovementRepository extends CrudRepository<EmpMovDto,String>{
	@Query(value = "SELECT * FROM EMPMOV ORDER BY mov_create DESC LIMIT 1;", nativeQuery = true)
	EmpMovDto findLastID();


	@Query(value = "SELECT * FROM EMPMOV n WHERE (n.mov_id =?1 OR n.mov_empid = ?1) ", nativeQuery = true)
	List<EmpMovDto> find(String cname);

}
