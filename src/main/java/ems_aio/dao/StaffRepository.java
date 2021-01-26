package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.MEMP001;




@Repository
public interface StaffRepository extends CrudRepository<MEMP001, String>{
	  @Query(value = "SELECT * FROM memp001 ", nativeQuery =	  true)
	  List<MEMP001> getvalid();
}
