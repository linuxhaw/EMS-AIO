package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ems_aio.dto.EmpMovDto;
import ems_aio.dto.StaffDto;



public interface MovementRepository extends CrudRepository<EmpMovDto,String>{
	
	

	@Query(value = "SELECT * FROM EMPMOV WHERE mov_process=?1 ORDER BY mov_create desc", nativeQuery = true)
	List<EmpMovDto> blacklist(String process);

	@Query(value = "SELECT * FROM EMPMOV ORDER BY mov_create DESC LIMIT 1;", nativeQuery = true)
	EmpMovDto findLastID();


	@Query(value = "SELECT * FROM EMPMOV  n inner join memp001 m on n.mov_empid_emp_id=m.emp_id WHERE m.emp_name=?1 or m.emp_id=?1 or n.mov_process=?1  ORDER BY mov_create desc", nativeQuery = true)
	Page<EmpMovDto> findSearchPagi(String cname,Pageable pageable);
	
	
	
	@Query(value = "SELECT * FROM EMPMOV   ORDER BY mov_create desc", nativeQuery = true)
	Page<EmpMovDto> findPagi(Pageable pageable);
	
	//SELECT * FROM EMPMOV n WHERE n.mov_id=?1  AND n.mov_process='BlackList' ORDER BY mov_create desc
	@Query(value = "SELECT * FROM EMPMOV n inner join memp001 m  on n.mov_empid_emp_id=m.emp_id WHERE (n.mov_empid_emp_id=?1 OR m.emp_name=?1)  AND n.mov_process='BlackList' ORDER BY mov_create desc", nativeQuery = true)
	Page<EmpMovDto> findSearchBlacklist(String cname,Pageable pageable);
	
	@Query(value = "SELECT * FROM EMPMOV WHERE mov_process='BlackList' ORDER BY mov_create desc", nativeQuery = true)
	Page<EmpMovDto> findBlacklist(Pageable pageable);

	@Query(value = "SELECT * FROM EMPMOV n WHERE n.mov_empid_emp_id=?1 ORDER BY mov_create desc", nativeQuery = true)
	List<EmpMovDto> stffmov(String cname);
	
	@Query(value = "SELECT * FROM EMPMOV n WHERE n.mov_empid_emp_id=?1 ORDER BY mov_create desc limit 1", nativeQuery = true)
	Optional<EmpMovDto> findLastmov(String id);
	
	//zaybohein staff movement pagi
	@Query(value = "SELECT * FROM EMPMOV  n  WHERE  n.mov_empid_emp_id=?1  ORDER BY mov_create desc", nativeQuery = true)
	Page<EmpMovDto> findStaffPagi(String id,Pageable pageable);
	//zaybohein staff movement search pagi

	@Query(value = "SELECT * FROM EMPMOV  n inner join memp001 m inner join mpos001 p on m.emp_pos_pos_id=p.pos_id  on n.mov_empid_emp_id=m.emp_id WHERE (m.emp_name=?1 or n.mov_process=?1 or p.pos_name=?1) and n.mov_empid_emp_id=?2 ORDER BY mov_create desc", nativeQuery = true)
	Page<EmpMovDto> findStaffSearchPagi(String search,String id,Pageable pageable);

	
}
