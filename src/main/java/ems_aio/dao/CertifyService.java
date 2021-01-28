package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ems_aio.dto.MCTF001;

//defining the business logic
@Service
public class CertifyService {
	@Autowired
	CertifyRepository repo;
//getting all books record by using the method findaAll() of	CrudRepository

	public List<MCTF001> getsearch(String id) {
		 List<MCTF001> list = (List<MCTF001>) repo.find(id); 
	 return list; }
	
	 public List<MCTF001> getAll() {
		 List<MCTF001> list = (List<MCTF001>) repo.getvalid(); 
	 return list; }
	 
	
	//zay
	public MCTF001 findLastID() {
		MCTF001 list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<MCTF001> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(MCTF001 data) {
		repo.save(data);
	}

//deleting a specific record by using the method deleteById() of CrudRepository

	public void delete(String code) {
		repo.deleteById(code);
	}

//updating a record
	public void update(MCTF001 data, String Code) {
		repo.save(data);
	}
	public Page<MCTF001> certiPagi(int pageNo,int pageSize){
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return this.repo.findAll(pageable);
	}
}