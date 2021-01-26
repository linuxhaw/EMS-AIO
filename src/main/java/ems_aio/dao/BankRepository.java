package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MBNK001;

//repository that extends CrudRepository
@Repository
public interface BankRepository extends CrudRepository<MBNK001, String> {

	@Query(value = "SELECT * FROM mbnk001 ORDER BY BNK_CREATE DESC LIMIT 1;", nativeQuery = true)
	MBNK001 findLastID();

	@Query(value = "SELECT * FROM mbnk001 WHERE BNK_status=1;", nativeQuery = true)
	List<MBNK001> getvalid();

	@Query(value = "SELECT * FROM mbnk001 n WHERE (n.BNK_ID =?1 OR n.BNK_NAME = ?1) AND BNK_status=1", nativeQuery = true)
	List<MBNK001> findrole(String cname);
}