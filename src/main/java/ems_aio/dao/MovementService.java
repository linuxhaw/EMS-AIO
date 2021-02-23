package ems_aio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ems_aio.dto.EmpMovDto;
import ems_aio.dto.StaffDto;

@Service
public class MovementService {

	@Autowired
	MovementRepository repo;


	// aung
	public EmpMovDto findLastID() {
		EmpMovDto list = repo.findLastID();
		return list;
	}

	// Update
	public void update(EmpMovDto data) {
		repo.save(data);
	}

//getting a specific record by using the method findById() of	CrudRepository

	public Optional<EmpMovDto> getByCode(String code) {

		return repo.findById(code);

	}

//saving a specific record by using the method save() of	CrudRepository

	public void save(EmpMovDto data) {
		repo.save(data);
	}

	public List<EmpMovDto> getBlackList() {
		List<EmpMovDto> list = (List<EmpMovDto>) repo.blacklist("blacklist");
		return list;
	}

	// zay
	public List<EmpMovDto> getStaffMov(String id) {
		List<EmpMovDto> list = (List<EmpMovDto>) repo.stffmov(id);
		return list;
	}

	public Page<EmpMovDto> movementStaffPagi(String id, int PageNo, int PageSize) {
		Pageable pageable = PageRequest.of(PageNo - 1, PageSize);
		return this.repo.findStaffPagi(id, pageable);

	}

	public Page<EmpMovDto> movementStaffSearchPagi(String search, String id, int PageNo, int PageSize) {
		Pageable pageable = PageRequest.of(PageNo - 1, PageSize);
		return this.repo.findStaffSearchPagi(search, id, pageable);
	}

//	Hlwann
//	pagi_service with findAll method
	public Page<EmpMovDto> movementSearchPagi(String cname, int PageNo, int PageSize) {
		Pageable pageable = PageRequest.of(PageNo - 1, PageSize);
		return this.repo.findSearchPagi(cname, pageable);

	}

	public Page<EmpMovDto> movementPagi(int PageNo, int PageSize) {
		Pageable pageable = PageRequest.of(PageNo - 1, PageSize);
		return this.repo.findPagi(pageable);

	}

	public Page<EmpMovDto> blacklistSearch(String cname, int PageNo, int PageSize) {
		Pageable pageable = PageRequest.of(PageNo - 1, PageSize);
		return this.repo.findSearchBlacklist(cname, pageable);

	}

	public Page<EmpMovDto> blacklist(int PageNo, int PageSize) {
		Pageable pageable = PageRequest.of(PageNo - 1, PageSize);
		return this.repo.findBlacklist(pageable);

	}

	// get employee current movement
	public Optional<EmpMovDto> getMovLast(String id) {
		return repo.findLastmov(id);
	}
}
