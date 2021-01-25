package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MDEP001;

//repository that extends CrudRepository
@Repository
public interface DepartmentRepository extends CrudRepository<MDEP001, String> {

	@Query(value = "SELECT * FROM MDEP001 ORDER BY DEP_CREATE DESC LIMIT 1;", nativeQuery = true)
	MDEP001 findLastID();

	@Query(value = "SELECT * FROM MDEP001 WHERE DEP_status=1;", nativeQuery = true)
	List<MDEP001> getvalid();

	@Query(value = "SELECT * FROM MDEP001 n WHERE (n.DEP_ID =?1 OR n.DEP_NAME = ?1 OR n.DEP_HEAD = ?1) AND DEP_status=1", nativeQuery = true)
	List<MDEP001> find(String cname);
}