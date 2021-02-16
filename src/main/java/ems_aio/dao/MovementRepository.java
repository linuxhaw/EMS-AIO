package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ems_aio.dto.EmpMovDto;



public interface MovementRepository extends CrudRepository<EmpMovDto,String>{
	
	

	@Query(value = "SELECT * FROM empmov WHERE mov_process=?1 ORDER BY mov_create", nativeQuery = true)
	List<EmpMovDto> blacklist(String process);

	@Query(value = "SELECT * FROM empmov ORDER BY mov_create DESC LIMIT 1;", nativeQuery = true)
	EmpMovDto findLastID();


	@Query(value = "SELECT * FROM empmov n WHERE (n.mov_id =?1 OR n.mov_empid = ?1) ", nativeQuery = true)
	List<EmpMovDto> find(String cname);

	@Query(value = "SELECT * FROM empmov ORDER BY mov_create", nativeQuery = true)
	List<EmpMovDto> gethistory();
	
	/*
	 * @Query(value = "SELECT * FROM EMPMOV n WHERE (n.mov =?1 ) ", nativeQuery =
	 * true) List<EmpMovDto> blacklist = null;
	 */
}
