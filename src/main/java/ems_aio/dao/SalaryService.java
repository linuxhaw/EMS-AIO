package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.EmployeeSalaryDto;


@Service
public class SalaryService {

	@Autowired
	SalaryRepository repo;
	
	public List<EmployeeSalaryDto> getsearch(String id) {
		 List<EmployeeSalaryDto> list = (List<EmployeeSalaryDto>) repo.find(id); 
	 return list; }
	
	 public List<EmployeeSalaryDto> getAll() {
		 List<EmployeeSalaryDto> list = (List<EmployeeSalaryDto>) repo.getvalid(); 
	 return list; }
	 
	 
	
	//aung
	public EmployeeSalaryDto findLastID() {
		EmployeeSalaryDto list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<EmployeeSalaryDto> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(EmployeeSalaryDto data) {
		repo.save(data);
	}

}
