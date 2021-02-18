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

import ems_aio.dao.DepartmentService;
import ems_aio.dao.PositionService;
import ems_aio.dao.StaffService;
import ems_aio.dto.EmpMovDto;
import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.StaffDto;
import ems_aio.model.DepReportBean;
import ems_aio.model.MovementBean;
import ems_aio.model.PosReportBean;
import ems_aio.model.StaffBean;
import ems_aio.model.UserBean;

@Controller
public class ManagerViewController {
	@Autowired
	private StaffService StaffService;
	@Autowired
	private DepartmentService serv;
	@Autowired
	private PositionService positionserv;
	@Autowired
	private ems_aio.dao.MovementService MovementService;
		
	@RequestMapping(value="/ManagerProfile" ,method=RequestMethod.GET)
	public ModelAndView setupStaffList() {
		return new ModelAndView("EMS-MRI-003","user",new UserBean());
	}
	
//	@RequestMapping(value="/MngStaffInformation" ,method=RequestMethod.GET)
//	public ModelAndView MngStaffInformation() {
//		return new ModelAndView("EMS-MRS-003","user",new UserBean());
//	}
	@RequestMapping(value = "/MngStaffInformation", method = RequestMethod.GET)
	public ModelAndView MngStaffInformation(Model model) {
		List<StaffDto> list;
		list = StaffService.getAll();
		StaffBean bean=new StaffBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-MRS-003", "stafflist", list);
	}
	
	@RequestMapping(value = "/MngStaffInformationDetail", method = RequestMethod.GET)
	public ModelAndView MngStaffInformationDetail(@RequestParam("id")String id, ModelMap model,HttpServletRequest request) {
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

		
	return new ModelAndView("EMS-MRS-004", "bean", staff);
		
	}
	
//	@RequestMapping(value="/MngDepartments" ,method=RequestMethod.GET)
//	public ModelAndView MngDepartments() {
//		return new ModelAndView("EMS-MRD-003","user",new UserBean());
//	}
	@RequestMapping(value = "/MngDepartments", method = RequestMethod.GET)
	public String MngDepartments(Model model,HttpServletRequest request) {
		List<MDEP001> list=serv.getAll();
		DepReportBean bean=new DepReportBean();
		model.addAttribute("bean", bean);
		request.getSession().setAttribute("deplist", list);
		return  "EMS-MRD-003";
	}
	
//	@RequestMapping(value="/MngPositions" ,method=RequestMethod.GET)
//	public ModelAndView MngPositions() {
//		return new ModelAndView("EMS-MRP-003","user",new UserBean());
//	}
	
	@RequestMapping(value = "/MngPositions", method = RequestMethod.GET)
	public String MngPositions(Model model,HttpServletRequest request) {
		List<MPOS001> list=positionserv.getAll();
		PosReportBean bean=new PosReportBean();
		model.addAttribute("bean", bean);
		request.getSession().setAttribute("poslist", list);
		return  "EMS-MRP-003";
	}
	
//	@RequestMapping(value="/MngBlacklists" ,method=RequestMethod.GET)
//	public ModelAndView MngBanks() {
//		return new ModelAndView("EMS-MRB-003","user",new UserBean());
//	}
	@RequestMapping(value="/MngBlacklists" ,method=RequestMethod.GET)
	public ModelAndView MngBlacklists(Model model) {
		List<EmpMovDto> list;
		list = MovementService.getBlackList();
		StaffBean bean=new StaffBean();
		model.addAttribute("bean", bean);
		//return new ModelAndView("EMS-STI-003", "blacklist", list);
		return new ModelAndView("EMS-MRB-003","blacklist",list);
	}
}
