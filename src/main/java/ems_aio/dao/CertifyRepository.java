package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MCTF001;

//repository that extends CrudRepository
@Repository
public interface CertifyRepository extends CrudRepository<MCTF001, String> {

	@Query(value = "SELECT * FROM MCTF001 ORDER BY CTF_CREATE DESC LIMIT 1;", nativeQuery = true)
	MCTF001 findLastID();

	@Query(value = "SELECT * FROM MCTF001 WHERE CTF_status=1;", nativeQuery = true)
	List<MCTF001> getvalid();

	@Query(value = "SELECT * FROM MCTF001 n WHERE (n.CTF_ID =?1 OR n.CTF_NAME = ?1) AND CTF_status=1", nativeQuery = true)
	List<MCTF001> find(String cname);
}