package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MCTF001;
import ems_aio.dto.MDEP001;

//repository that extends CrudRepository
@Repository
public interface DepartmentRepository extends CrudRepository<MDEP001, String> {

	@Query(value = "SELECT * FROM mdep001 ORDER BY DEP_CREATE DESC LIMIT 1;", nativeQuery = true)
	MDEP001 findLastID();

	@Query(value = "SELECT * FROM mdep001 WHERE DEP_status=1;", nativeQuery = true)
	List<MDEP001> getvalid();

	@Query(value = "SELECT * FROM mdep001 n WHERE n.DEP_status=1 AND (n.DEP_id=?1 OR n.DEP_name=?1 OR n.DEP_loc=?1 OR n.DEP_head=?1)"
			 , nativeQuery = true)
	  Page<MDEP001> findAll(String cname,Pageable pageable);
	  @Query(value = "SELECT * FROM mdep001 WHERE mdep001.DEP_status=1", nativeQuery =	true)
	  Page<MDEP001> findQuery(Pageable pageable);
}