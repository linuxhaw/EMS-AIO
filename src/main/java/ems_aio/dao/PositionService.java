package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.MPOS001;

//defining the business logic
@Service
public class PositionService {
	@Autowired
	PositionRepository repo;
//getting all books record by using the method findaAll() of	CrudRepository

	public List<MPOS001> getsearchPosition(String id) {
		 List<MPOS001> list = (List<MPOS001>) repo.findrole(id); 
	 return list; }
	
	 public List<MPOS001> getAll() {
		 List<MPOS001> list = (List<MPOS001>) repo.getvalid(); 
	 return list; }
	 
	
	//zay
	public MPOS001 findLastID() {
		MPOS001 list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<MPOS001> getPositionByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(MPOS001 roles) {
		repo.save(roles);
	}

//deleting a specific record by using the method deleteById() of CrudRepository

	public void delete(String code) {
		repo.deleteById(code);
	}

//updating a record
	public void update(MPOS001 books, String Code) {
		repo.save(books);
	}
}