package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.EmpMovDto;


@Service
public class MovementService {

	@Autowired
	MovementRepository repo;
	
	public List<EmpMovDto> getsearch(String id) {
		 List<EmpMovDto> list = (List<EmpMovDto>) repo.find(id); 
	 return list; }
	
	/*
	 * public List<EmpSalDto> getAll() { List<EmpSalDto> list = (List<EmpSalDto>)
	 * repo.getvalid(); return list; }
	 */
	 
	
	//aung
	public EmpMovDto findLastID() {
		EmpMovDto list = repo.findLastID();
		return list;
	}
	

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<EmpMovDto> getByCode(String code) {

		return repo.findById(code);

	}
	
//saving a specific record by using the method save() of	CrudRepository

	public void save(EmpMovDto data) {
		repo.save(data);
	}

}
