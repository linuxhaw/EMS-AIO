package ems_aio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ems_aio.dao.MovementService;
import ems_aio.dao.SalaryService;
import ems_aio.dao.StaffService;
import ems_aio.model.UserBean;
@Controller
public class StaffViewController {
	@Autowired
	private StaffService StaffService;
	@Autowired
	private MovementService MovementService;
	@Autowired
	private SalaryService SalaryService;
	
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
