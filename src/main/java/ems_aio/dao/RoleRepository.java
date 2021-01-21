package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MROL001;

//repository that extends CrudRepository
@Repository
public interface RoleRepository extends CrudRepository<MROL001, String> {
	
	//zay
	@Query(value = "SELECT * FROM mrol001 ORDER BY ROL_CREATE DESC LIMIT 1;", nativeQuery = true)
	MROL001 findLastID();
}