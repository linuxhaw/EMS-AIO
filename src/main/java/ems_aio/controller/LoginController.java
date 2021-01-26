package ems_aio.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<<<<<<< HEAD

import ems_aio.dto.User;
import ems_aio.model.UserBean;
import ems_aio.service.Login_Service;
=======
import org.springframework.web.servlet.ModelAndView;
>>>>>>> 657aeebe99d3ab8c3ac0d40650c70a579fa2e4a0



import ems_aio.model.UserBean;

@Controller
public class LoginController {
	@Autowired
	Login_Service service;
	
@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("user", new UserBean());

		return "EMS-STI-001";
	}
	
	@RequestMapping(value="/setupRepoetBlackList" ,method=RequestMethod.GET)
	public ModelAndView setupRepoetBlackList() {
		return new ModelAndView("EMS-ARB-003","user",new UserBean());
	}
	
	@RequestMapping(value="/setupReportDepartment" ,method=RequestMethod.GET)
	public ModelAndView setupReportDepartment() {
		return new ModelAndView("EMS-ARD-003","user",new UserBean());
	}
	
	@RequestMapping(value="/setupReportPosition" ,method=RequestMethod.GET)
	public ModelAndView setupReportPosition() {
		return new ModelAndView("EMS-ARP-003","user",new UserBean());
	}
	
	@RequestMapping(value="/setupReportStaff" ,method=RequestMethod.GET)
	public ModelAndView setupReportStaff() {
		return new ModelAndView("EMS-ARS-003","user",new UserBean());
	}
	
	@RequestMapping(value="/setupReportStaffList" ,method=RequestMethod.GET)
	public ModelAndView setupReportStaffList() {
		return new ModelAndView("EMS-ARS-004","user",new UserBean());
	}
	
	@RequestMapping(value="/setupPosition" ,method=RequestMethod.GET)
	public ModelAndView setupPosition() {
		return new ModelAndView("EMS-MSP-001","user",new UserBean());
	}
	
	@RequestMapping(value="/setupEditPosition" ,method=RequestMethod.GET)
	public ModelAndView setupEditPosition() {
		return new ModelAndView("EMS-MSP-002","user",new UserBean());
	}
	
	@RequestMapping(value="/setupPositionList" ,method=RequestMethod.GET)
	public ModelAndView setupPositionList() {
		return new ModelAndView("EMS-MSP-003","user",new UserBean());
	}
	
	@RequestMapping(value="/setupBank" ,method=RequestMethod.GET)
	public ModelAndView setupBank() {
		return new ModelAndView("EMS-MSB-001","user",new UserBean());
	}
	@RequestMapping(value="/setupEditBank" ,method=RequestMethod.GET)
	public ModelAndView setupEditPositionBank() {
		return new ModelAndView("EMS-MSB-002","user",new UserBean());
	}
	@RequestMapping(value="/setupBankList" ,method=RequestMethod.GET)
	public ModelAndView setupBankList() {
		return new ModelAndView("EMS-MSB-003","user",new UserBean());
	}
	
	@RequestMapping(value="/setupCertificate" ,method=RequestMethod.GET)
	public ModelAndView setupCertificate() {
		return new ModelAndView("EMS-MSC-001","user",new UserBean());
	}
	@RequestMapping(value="/setupEditCertificate" ,method=RequestMethod.GET)
	public ModelAndView setupEditCertificate() {
		return new ModelAndView("EMS-MSC-002","user",new UserBean());
	}
	@RequestMapping(value="/setupCertificateList" ,method=RequestMethod.GET)
	public ModelAndView setupCertificateList() {
		return new ModelAndView("EMS-MSC-003","user",new UserBean());
	}
	@RequestMapping(value="/setupDepartment" ,method=RequestMethod.GET)
	public ModelAndView setupDepartment() {
		return new ModelAndView("EMS-MSD-001","user",new UserBean());
	}
	@RequestMapping(value="/setupEditDepartment" ,method=RequestMethod.GET)
	public ModelAndView setupEditDepartment() {
		return new ModelAndView("EMS-MSD-002","user",new UserBean());
	}
	@RequestMapping(value="/setupDepartmentList" ,method=RequestMethod.GET)
	public ModelAndView setupDepartmentList() {
		return new ModelAndView("EMS-MSD-003","user",new UserBean());
	}
	@RequestMapping(value="/setupQualification" ,method=RequestMethod.GET)
	public ModelAndView setupQualification() {
		return new ModelAndView("EMS-MSQ-001","user",new UserBean());
	}
	@RequestMapping(value="/setupEditQualification" ,method=RequestMethod.GET)
	public ModelAndView setupEditQualification() {
		return new ModelAndView("EMS-MSQ-002","user",new UserBean());
	}
	@RequestMapping(value="/setupQualificationList" ,method=RequestMethod.GET)
	public ModelAndView setupQualificationList() {
		return new ModelAndView("EMS-MSQ-003","user",new UserBean());
	}
	@RequestMapping(value="/setupRole" ,method=RequestMethod.GET)
	public ModelAndView setupRole() {
		return new ModelAndView("EMS-MSR-001","user",new UserBean());
	}
	@RequestMapping(value="/setupEditRole" ,method=RequestMethod.GET)
	public ModelAndView setupEditRole() {
		return new ModelAndView("EMS-MSR-002","user",new UserBean());
	}
	@RequestMapping(value="/setupRoleList" ,method=RequestMethod.GET)
	public ModelAndView setupRoleList() {
		return new ModelAndView("EMS-MSR-003","user",new UserBean());
	}
	@RequestMapping(value="/setupPayRollList" ,method=RequestMethod.GET)
	public ModelAndView setupPayRollList() {
		return new ModelAndView("EMS-PYL-003","user",new UserBean());
	}
	@RequestMapping(value="/addPayRoll" ,method=RequestMethod.GET)
	public ModelAndView addPayRoll() {
		return new ModelAndView("EMS-PYR-001","user",new UserBean());
	}
	@RequestMapping(value="/setupPayRollHistory" ,method=RequestMethod.GET)
	public ModelAndView setupPayRollHistory() {
		return new ModelAndView("EMS-PYR-003","user",new UserBean());
	}
	@RequestMapping(value="/setupMovementHistory" ,method=RequestMethod.GET)
	public ModelAndView setupMovementHistory() {
		return new ModelAndView("EMS-STM-003","user",new UserBean());
	}
	@RequestMapping(value="/setupPositionChange" ,method=RequestMethod.GET)
	public ModelAndView setupPositionChange() {
		return new ModelAndView("EMS-STM-002","user",new UserBean());
	}
	@RequestMapping(value="/setupaddStaff" ,method=RequestMethod.GET)
	public ModelAndView setupaddStaff() {
		return new ModelAndView("EMS-STI-001","user",new UserBean());
	}
	@RequestMapping(value="/setupEditStaff" ,method=RequestMethod.GET)
	public ModelAndView setupEditStaff() {
		return new ModelAndView("EMS-STI-002","user",new UserBean());
	}
	@RequestMapping(value="/setupStaffList" ,method=RequestMethod.GET)
	public ModelAndView setupStaffList() {
		return new ModelAndView("EMS-STI-003","user",new UserBean());
	}
}
>>>>>>> 657aeebe99d3ab8c3ac0d40650c70a579fa2e4a0
