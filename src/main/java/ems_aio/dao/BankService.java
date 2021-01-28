package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ems_aio.dto.MBNK001;
import ems_aio.dto.MDEP001;

//defining the business logic
@Service
public class BankService {
	@Autowired
	BankRepository repo;
//getting all books record by using the method findaAll() of	CrudRepository

	public List<MBNK001> getsearch(String id) {
		 List<MBNK001> list = (List<MBNK001>) repo.findrole(id); 
	 return list; }
	
	 public List<MBNK001> getAll() {
		 List<MBNK001> list = (List<MBNK001>) repo.getvalid(); 
	 return list; }
	 
	
	//zay
	public MBNK001 findLastID() {
		MBNK001 list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<MBNK001> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(MBNK001 roles) {
		repo.save(roles);
	}

//deleting a specific record by using the method deleteById() of CrudRepository

	public void delete(String code) {
		repo.deleteById(code);
	}

//updating a record
	public void update(MBNK001 data, String Code) {
		repo.save(data);
	}
	public Page<MBNK001>bankPagi(int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
		return this.repo.findAll(pageable);
		
	}
}