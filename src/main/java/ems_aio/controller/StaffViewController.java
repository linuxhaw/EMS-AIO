package ems_aio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ems_aio.model.UserBean;
@Controller
public class StaffViewController {
	@RequestMapping(value="/StaffProfile" ,method=RequestMethod.GET)
	public ModelAndView StaffProfile() {
		return new ModelAndView("EMS-SRP-003","user",new UserBean());
	}
	
	@RequestMapping(value="/StaffMovement" ,method=RequestMethod.GET)
	public ModelAndView StaffMovement() {
		return new ModelAndView("EMS-SRM-003","user",new UserBean());
	}
	
	@RequestMapping(value="/StaffSalary" ,method=RequestMethod.GET)
	public ModelAndView StaffSalary() {
		return new ModelAndView("EMS-SRS-003","user",new UserBean());
	}
}
