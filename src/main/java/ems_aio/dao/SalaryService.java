package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ems_aio.dto.EmpMovDto;
import ems_aio.dto.EmpSalDto;
import ems_aio.dto.MROL001;


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
//	Hlwann
//	pagi_service with findAll method
	public Page<EmpSalDto>salarySearchPagi(String cname,	int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
		return this.repo.findSearchPagi(cname, pageable); 
	}
	
	public Page<EmpSalDto>salaryPagi(int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
		return this.repo.findPagi(pageable);
		
	}


	public Page<EmpSalDto>salaryStaffPagi(String cname,	int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
		return this.repo.salaryStaffPagi(cname, pageable); 
	}
	
	public Page<EmpSalDto>salaryStaffSearchPagi(String cname,String date1,String date2,int PageNo,int PageSize){
		Pageable pageable=PageRequest.of(PageNo-1, PageSize);
		return this.repo.salaryStaffSearchPagi(cname,date1,date2, pageable); 
	}
	//zay
	
	/*public List<EmpSalDto> getStaffSalSearch(String id,String fromdate,String todate) {
		List<EmpSalDto> list= (List<EmpSalDto>) repo.stffsalsearch(id,fromdate,todate);
		return list;
	}*/
}
