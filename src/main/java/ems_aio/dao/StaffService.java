package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.StaffDto;

//defining the business logic
@Service
public class StaffService {
	@Autowired
	StaffRepository repo;
//getting all books record by using the method findaAll() of	CrudRepository

	public List<StaffDto> getsearch(String id) {
		 List<StaffDto> list = (List<StaffDto>) repo.find(id); 
	 return list; }
	
	 public List<StaffDto> getAll() {
		 List<StaffDto> list = (List<StaffDto>) repo.getvalid(); 
	 return list; }
	 
	
	//zay
	public StaffDto findLastID() {
		StaffDto list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<StaffDto> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(StaffDto data) {
		repo.save(data);
	}

//deleting a specific record by using the method deleteById() of CrudRepository

	public void delete(String code) {
		repo.deleteById(code);
	}

//updating a record
	public void update(StaffDto data, String Code) {
		repo.save(data);
	}

}