package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ems_aio.dto.MPOS001;
import ems_aio.dto.MQUL001;

//defining the business logic
@Service
public class QualifyService {
	@Autowired
	QualifyRepository repo;
//getting all books record by using the method findaAll() of	CrudRepository

//	public List<MQUL001> getsearch(String id) {
//		 List<MQUL001> list = (List<MQUL001>) repo.find(id); 
//	 return list; }
//	
	 public List<MQUL001> getAll() {
		 List<MQUL001> list = (List<MQUL001>) repo.getvalid(); 
	 return list; }
	 
	
	//zay
	public MQUL001 findLastID() {
		MQUL001 list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<MQUL001> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(MQUL001 data) {
		repo.save(data);
	}

//deleting a specific record by using the method deleteById() of CrudRepository

	public void delete(String code) {
		repo.deleteById(code);
	}

//updating a record
	public void update(MQUL001 data, String Code) {
		repo.save(data);
	}
//	Hlwann
//	pagi_service with findAll method
	public Page<MQUL001>qulPagi(String cname,	int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
		return this.repo.findAll(cname,pageable);
		
	}
	public Page<MQUL001>qulPagiQuery(int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
		return this.repo.findQuery(pageable);
		
	}
}