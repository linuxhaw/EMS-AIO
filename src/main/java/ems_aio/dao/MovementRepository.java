package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ems_aio.dto.EmpMovDto;
import ems_aio.dto.StaffDto;



public interface MovementRepository extends CrudRepository<EmpMovDto,String>{
	
	

	@Query(value = "SELECT * FROM EMPMOV WHERE mov_process=?1 ORDER BY mov_create", nativeQuery = true)
	List<EmpMovDto> blacklist(String process);

	@Query(value = "SELECT * FROM EMPMOV ORDER BY mov_create DESC LIMIT 1;", nativeQuery = true)
	EmpMovDto findLastID();


	@Query(value = "SELECT * FROM EMPMOV n WHERE (n.mov_id =?1 OR n.mov_empid = ?1) ", nativeQuery = true)
	List<EmpMovDto> find(String cname);

	@Query(value = "SELECT * FROM EMPMOV ORDER BY mov_create", nativeQuery = true)
	List<EmpMovDto> gethistory();
	
	/*
	 * @Query(value = "SELECT * FROM EMPMOV n WHERE (n.mov =?1 ) ", nativeQuery =
	 * true) List<EmpMovDto> blacklist = null;
	 */
	@Query(value = "SELECT * FROM EMPMOV n WHERE n.mov_id=?1 ", nativeQuery = true)
	Page<EmpMovDto> findSearchPagi(String cname,Pageable pageable);
	@Query(value = "SELECT * FROM EMPMOV  ", nativeQuery = true)
	Page<EmpMovDto> findPagi(Pageable pageable);
	
	@Query(value = "SELECT * FROM EMPMOV n WHERE n.mov_id=?1 AND n.mov_process=?1 ORDER BY mov_create", nativeQuery = true)
	Page<EmpMovDto> findSearchBlacklist(String cname,Pageable pageable);
	@Query(value = "SELECT * FROM EMPMOV WHERE mov_process=?1 ORDER BY mov_create", nativeQuery = true)
	Page<EmpMovDto> findBlacklist(Pageable pageable);
}
