package ems_aio.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import ems_aio.dao.StaffService;
import ems_aio.dto.StaffDto;
import ems_aio.model.StaffBean;

@Controller
public class StaffReportController {

	@Autowired
	private StaffService StaffService;
	
	@RequestMapping(value = "/displayreportstaff", method = RequestMethod.GET)
	public ModelAndView displayreportstaff(Model model) {
		List<StaffDto> list;
		list = StaffService.getAll();
		StaffBean bean=new StaffBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-ARS-003", "stafflist", list);
	}
	@RequestMapping(value = "/setupstaffreport", method = RequestMethod.GET)
	public ModelAndView setuproleupdate(@RequestParam("id")String id, ModelMap model,HttpServletRequest request) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Optional<StaffDto> dtoget = StaffService.getByCode(id);
		StaffDto dto1=dtoget.get();
		StaffBean staff = new StaffBean();
		staff.setId(dto1.getEmp_id());
		staff.setName(dto1.getEmp_name());
		staff.setPassword(dto1.getEmp_password());
		staff.setNrc(dto1.getEmp_nrc());
		staff.setEmail(dto1.getEmp_email());
		staff.setPhone(dto1.getEmp_phone());
		staff.setSalary(dto1.getEmp_payroll());
		staff.setBankAcc(dto1.getEmp_bnkacc());
		staff.setBank(dto1.getEmp_bnk());
		staff.setAddress(dto1.getEmp_address());
		staff.setRegister(dateFormat.format(dto1.getEmp_register()));
		staff.setBirthday(dateFormat.format(dto1.getEmp_birthday()));
		staff.setGender(dto1.getEmp_gender());
		staff.setMarrage(dto1.getEmp_marrage());
		staff.setReligion(dto1.getEmp_religion());
		staff.setNation(dto1.getEmp_nationality());
		staff.setDepartment(dto1.getEmp_dep());
		staff.setRole(dto1.getEmp_rol());
		staff.setPosition(dto1.getEmp_pos());
		staff.setCertify(dto1.getCtf());
		staff.setQualify(dto1.getQul());

		
	return new ModelAndView("EMS-ARS-004", "bean", staff);
		
	}
	
}
