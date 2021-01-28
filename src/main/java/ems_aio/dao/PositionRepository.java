package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MPOS001;

//repository that extends CrudRepository
@Repository
public interface PositionRepository extends CrudRepository<MPOS001, String> {
	
	//zay
	@Query(value = "SELECT * FROM mpos001 ORDER BY POS_CREATE DESC LIMIT 1;", nativeQuery = true)
	MPOS001 findLastID();
	
	
	  @Query(value = "SELECT * FROM mpos001 WHERE pos_status=1;", nativeQuery =	  true)
	  List<MPOS001> getvalid();
	
	  @Query(value = "SELECT * FROM mpos001 n WHERE (n.pos_id =?1 OR n.pos_name = ?1) AND pos_status=1", nativeQuery = true)
		List<MPOS001> findrole(String cname);
	  /*Hlawann added Pagination
	   *set pagi_repository up
	   * */
	  Page<MPOS001> findAll(Pageable pageable);
}