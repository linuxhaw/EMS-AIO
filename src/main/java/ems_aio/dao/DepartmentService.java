package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.MDEP001;

//defining the business logic
@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository repo;
//getting all books record by using the method findaAll() of	CrudRepository

	public List<MDEP001> getsearch(String id) {
		 List<MDEP001> list = (List<MDEP001>) repo.find(id); 
	 return list; }
	
	 public List<MDEP001> getAll() {
		 List<MDEP001> list = (List<MDEP001>) repo.getvalid(); 
	 return list; }
	 
	
	//zay
	public MDEP001 findLastID() {
		MDEP001 list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<MDEP001> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(MDEP001 data) {
		repo.save(data);
	}

//deleting a specific record by using the method deleteById() of CrudRepository

	public void delete(String code) {
		repo.deleteById(code);
	}

//updating a record
	public void update(MDEP001 data, String Code) {
		repo.save(data);
	}
}