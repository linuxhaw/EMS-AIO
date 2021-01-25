package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MEMP001;

//repository that extends CrudRepository
@Repository
public interface StaffRepository extends CrudRepository<MEMP001, String> {

	@Query(value = "SELECT * FROM MEMP001 ORDER BY EMP_CREATE DESC LIMIT 1;", nativeQuery = true)
	MEMP001 findLastID();

	@Query(value = "SELECT * FROM MEMP001 WHERE EMP_status=1;", nativeQuery = true)
	List<MEMP001> getvalid();

	@Query(value = "SELECT * FROM MEMP001 n WHERE (n.EMP_ID =?1 OR n.EMP_NAME = ?1) AND EMP_status=1", nativeQuery = true)
	List<MEMP001> find(String cname);
}