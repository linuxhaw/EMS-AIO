package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.EmpSalDto;


@Service
public class SalaryService {

	@Autowired
	SalaryRepository repo;
	
	public List<EmpSalDto> getsearch(String id) {
		 List<EmpSalDto> list = (List<EmpSalDto>) repo.find(id); 
	 return list; }
	
	 public List<EmpSalDto> getAll() {
		 List<EmpSalDto> list = (List<EmpSalDto>) repo.getvalid(); 
	 return list; }
	 
	 
	
	//aung
	public EmpSalDto findLastID() {
		EmpSalDto list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<EmpSalDto> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(EmpSalDto data) {
		repo.save(data);
	}

}
