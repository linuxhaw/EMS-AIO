package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ems_aio.dto.Role;

//defining the business logic
@Service
public class RoleService {
	@Autowired
	RoleRepository RoleRepository;
//getting all books record by using the method findaAll() of	CrudRepository

	public List<Role> getAll() {
		List<Role> list = (List<Role>) RoleRepository.findAll();
		return list;
	}
	
	public List<Role> getAllById(String id) {
		List<Role> list = (List<Role>) RoleRepository.findAllByIdS(id);
		return list;
	}
	
	public List<Role> getAllByName(String name) {
		List<Role> list = (List<Role>) RoleRepository.findAllByNameS(name);
		return list;
	}
	
	public List<Role> getAllByClass(String classe) {
		List<Role> list = (List<Role>) RoleRepository.findAllByClassS(classe);
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<Role> getStudentByCode(String code) {

		return RoleRepository.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(Role roles) {
		RoleRepository.save(roles);
	}

//deleting a specific record by using the method deleteById() of CrudRepository

	public void delete(String code) {
		RoleRepository.deleteById(code);
	}

//updating a record
	public void update(Role books, String bookCode) {
		RoleRepository.save(books);
	}
}