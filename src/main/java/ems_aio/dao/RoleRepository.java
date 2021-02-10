package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MROL001;

//repository that extends CrudRepository
@Repository
public interface RoleRepository extends CrudRepository<MROL001, String> {
	

	@Query(value = "SELECT * FROM mrol001 ORDER BY ROL_CREATE DESC LIMIT 1;", nativeQuery = true)
	MROL001 findLastID();
	
	
	  @Query(value = "SELECT * FROM mrol001 WHERE rol_status=1;", nativeQuery =	  true)
	  List<MROL001> getvalid();
	
	
//		List<MROL001> findrole(String cname);
	  @Query(value = "SELECT * FROM mrol001 n WHERE n.rol_status=1 AND (n.rol_id=?1 OR n.rol_name=?1)"
			 , nativeQuery = true)
	  Page<MROL001> findAll(String cname,Pageable pageable);
	  @Query(value = "SELECT * FROM mrol001 WHERE mrol001.rol_status=1", nativeQuery =	true)
	  Page<MROL001> findQuery(Pageable pageable);

}