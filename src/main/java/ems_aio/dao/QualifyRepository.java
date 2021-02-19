package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MPOS001;
import ems_aio.dto.MQUL001;

//repository that extends CrudRepository
@Repository
public interface QualifyRepository extends CrudRepository<MQUL001, String> {

	@Query(value = "SELECT * FROM mqul001 ORDER BY QUL_CREATE DESC LIMIT 1;", nativeQuery = true)
	MQUL001 findLastID();

	@Query(value = "SELECT * FROM mqul001 WHERE QUL_status=1;", nativeQuery = true)
	List<MQUL001> getvalid();

	 @Query(value = "SELECT * FROM mqul001 n WHERE n.QUL_status=1 AND (n.QUL_id=?1 OR n.QUL_name=?1 OR n.QUL_school=?1)"
			 , nativeQuery = true)
	  Page<MQUL001> findAll(String cname,Pageable pageable);
	  @Query(value = "SELECT * FROM mqul001 WHERE mqul001.QUL_status=1", nativeQuery =	true)
	  Page<MQUL001> findQuery(Pageable pageable);
}