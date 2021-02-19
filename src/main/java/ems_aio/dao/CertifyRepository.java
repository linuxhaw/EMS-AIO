package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MBNK001;
import ems_aio.dto.MCTF001;

//repository that extends CrudRepository
@Repository
public interface CertifyRepository extends CrudRepository<MCTF001, String> {

	@Query(value = "SELECT * FROM mctf001 ORDER BY CTF_CREATE DESC LIMIT 1;", nativeQuery = true)
	MCTF001 findLastID();

	@Query(value = "SELECT * FROM mctf001 WHERE CTF_status=1;", nativeQuery = true)
	List<MCTF001> getvalid();

	 @Query(value = "SELECT * FROM mctf001 n WHERE n.CTF_status=1 AND (n.CTF_id=?1 OR n.CTF_name=?1 OR n.CTF_school=?1)"
			 , nativeQuery = true)
	  Page<MCTF001> findAll(String cname,Pageable pageable);
	  @Query(value = "SELECT * FROM mctf001 WHERE mctf001.CTF_status=1", nativeQuery =	true)
	  Page<MCTF001> findQuery(Pageable pageable);
}