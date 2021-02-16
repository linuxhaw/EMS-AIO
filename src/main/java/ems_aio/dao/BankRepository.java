package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MBNK001;
import ems_aio.dto.MROL001;

//repository that extends CrudRepository
@Repository
public interface BankRepository extends CrudRepository<MBNK001, String> {

	@Query(value = "SELECT * FROM mbnk001 ORDER BY BNK_CREATE DESC LIMIT 1;", nativeQuery = true)
	MBNK001 findLastID();

	@Query(value = "SELECT * FROM mbnk001 WHERE BNK_status=1;", nativeQuery = true)
	List<MBNK001> getvalid();

	  @Query(value = "SELECT * FROM mbnk001 n WHERE n.BNK_status=1 AND (n.BNK_id=?1 OR n.BNK_name=?1 OR n.BNK_phone=?1 OR n.BNK_loc=?1)"
				 , nativeQuery = true)
		  Page<MBNK001> findAll(String cname,Pageable pageable);
		  @Query(value = "SELECT * FROM mbnk001 WHERE mbnk001.BNK_status=1", nativeQuery =	true)
		  Page<MBNK001> findQuery(Pageable pageable);

}