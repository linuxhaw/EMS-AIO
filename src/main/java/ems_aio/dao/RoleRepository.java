package ems_aio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ems_aio.dto.Role;

//repository that extends CrudRepository
@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
	@Query(value = "SELECT * FROM student n WHERE n.student_id = ?1", nativeQuery = true)
	List<Role> findAllByIdS(String id);
	
	@Query(value = "SELECT * FROM student n WHERE n.student_name = ?1", nativeQuery = true)
	List<Role> findAllByNameS(String name);
	
	@Query(value = "SELECT * FROM student n WHERE n.class_name = ?1", nativeQuery = true)
	List<Role> findAllByClassS(String cname);
}