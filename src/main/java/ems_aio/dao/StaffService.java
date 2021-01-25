package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.MEMP001;

//defining the business logic
@Service
public class StaffService {
	@Autowired
	StaffRepository repo;
//getting all books record by using the method findaAll() of	CrudRepository

	public List<MEMP001> getsearch(String id) {
		 List<MEMP001> list = (List<MEMP001>) repo.find(id); 
	 return list; }
	
	 public List<MEMP001> getAll() {
		 List<MEMP001> list = (List<MEMP001>) repo.getvalid(); 
	 return list; }
	 
	
	//zay
	public MEMP001 findLastID() {
		MEMP001 list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<MEMP001> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(MEMP001 data) {
		repo.save(data);
	}

//deleting a specific record by using the method deleteById() of CrudRepository

	public void delete(String code) {
		repo.deleteById(code);
	}

//updating a record
	public void update(MEMP001 data, String Code) {
		repo.save(data);
	}
}