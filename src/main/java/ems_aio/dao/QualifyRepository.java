package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MQUL001;

//repository that extends CrudRepository
@Repository
public interface QualifyRepository extends CrudRepository<MQUL001, String> {

	@Query(value = "SELECT * FROM MQUL001 ORDER BY QUL_CREATE DESC LIMIT 1;", nativeQuery = true)
	MQUL001 findLastID();

	@Query(value = "SELECT * FROM MQUL001 WHERE QUL_status=1;", nativeQuery = true)
	List<MQUL001> getvalid();

	@Query(value = "SELECT * FROM MQUL001 n WHERE (n.QUL_ID =?1 OR n.QUL_NAME = ?1 OR n.CTF_SCHOOL = ?1) AND QUL_status=1", nativeQuery = true)
	List<MQUL001> find(String cname);
}