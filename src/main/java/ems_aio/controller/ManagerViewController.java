package ems_aio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ems_aio.model.UserBean;

@Controller
public class ManagerViewController {
		
	@RequestMapping(value="/ManagerProfile" ,method=RequestMethod.GET)
	public ModelAndView setupStaffList() {
		return new ModelAndView("EMS-MRI-003","user",new UserBean());
	}
	
	@RequestMapping(value="/MngStaffInformation" ,method=RequestMethod.GET)
	public ModelAndView MngStaffInformation() {
		return new ModelAndView("EMS-MRS-003","user",new UserBean());
	}
	
	@RequestMapping(value="/MngDepartments" ,method=RequestMethod.GET)
	public ModelAndView MngDepartments() {
		return new ModelAndView("EMS-MRD-003","user",new UserBean());
	}
	
	@RequestMapping(value="/MngPositions" ,method=RequestMethod.GET)
	public ModelAndView MngPositions() {
		return new ModelAndView("EMS-MRP-003","user",new UserBean());
	}
	
	@RequestMapping(value="/MngBanks" ,method=RequestMethod.GET)
	public ModelAndView MngBanks() {
		return new ModelAndView("EMS-MRB-003","user",new UserBean());
	}
	
}
