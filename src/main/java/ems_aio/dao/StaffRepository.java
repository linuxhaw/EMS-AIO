package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.StaffDto;

//repository that extends CrudRepository
@Repository
public interface StaffRepository extends CrudRepository<StaffDto, String> {

	@Query(value = "SELECT * FROM memp001 ORDER BY EMP_CREATE DESC LIMIT 1;", nativeQuery = true)
	StaffDto findLastID();

	@Query(value = "SELECT * FROM memp001 WHERE EMP_status=1;", nativeQuery = true)
	List<StaffDto> getvalid();

	@Query(value = "SELECT * FROM memp001 n WHERE (n.EMP_ID =?1 OR n.EMP_NAME = ?1) AND EMP_status=1 AND emp_blacklist=0", nativeQuery = true)
	List<StaffDto> find(String cname);


}