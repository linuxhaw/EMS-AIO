package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ems_aio.dto.MROL001;
import ems_aio.dto.StaffDto;

//defining the business logic
@Service
public class StaffService {
	@Autowired
	StaffRepository repo;
//getting all books record by using the method findaAll() of	CrudRepository

//	public List<StaffDto> getPosition(String id) {
//		 List<StaffDto> list = (List<StaffDto>) repo.find(id); 
//	 return list; }
	
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
	public List<StaffDto> getPosition(String pos) {
		 List<StaffDto> list = (List<StaffDto>) repo.findpos(pos); 
		 return list;
	}

	public  List<StaffDto> getDepartment(String id) {
		 List<StaffDto> list = (List<StaffDto>) repo.finddep(id); 
		 return list;
	}
//	Hlwann
//	pagi_service with findAll method
	public Page<StaffDto>staffSearchPagi(String cname,int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
	return this.repo.findSearchPagi(cname, pageable);
		
	}
	public Page<StaffDto>staffPagi(int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
		return this.repo.findPagi(pageable);
		
	}

}