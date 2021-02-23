package ems_aio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Query(value = "SELECT * FROM memp001 n inner join mrol001 r on n.emp_rol_rol_id=r.rol_id inner join mdep001 d on n.emp_dep_dep_id=d.dep_id inner join mpos001 m on n.emp_pos_pos_id=m.pos_id WHERE (n.EMP_ID =?1 OR n.EMP_NAME = ?1 OR m.pos_name=?1 OR d.dep_name=?1 OR r.rol_name=?1) AND EMP_status=1 AND emp_blacklist=0", nativeQuery = true)
	Page<StaffDto> findSearchPagi(String cname,Pageable pageable);
	
	@Query(value="SELECT * FROM memp001 n WHERE EMP_status=1 AND emp_blacklist=0 ",nativeQuery=true)
	Page<StaffDto> findPagi(Pageable pageable);

	@Query(value = "SELECT * FROM memp001 n WHERE (n.emp_pos_pos_id =?1 ) AND EMP_status=1 AND emp_blacklist=0", nativeQuery = true)
	List<StaffDto> findpos(String pos);

	@Query(value = "SELECT * FROM memp001 n WHERE (n.emp_dep_dep_id =?1 ) AND EMP_status=1 AND emp_blacklist=0", nativeQuery = true)
	List<StaffDto> finddep(String dep);
	
	@Query(value = "SELECT * FROM memp001 n WHERE (n.emp_dep_dep_id =?1 ) AND EMP_status=1 AND emp_blacklist=0", nativeQuery = true)
	Page<StaffDto> findDepList(Pageable pageable);
	
	@Query(value="SELECT * FROM memp001 n WHERE EMP_status=1 AND emp_blacklist=0 ORDER BY EMP_CREATE DESC LIMIT 5",nativeQuery=true)
	List<StaffDto> getlast();

}