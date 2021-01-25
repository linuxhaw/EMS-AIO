package ems_aio.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ems_aio.dao.StaffService;
import ems_aio.dao.RoleService;
import ems_aio.dto.MROL001;
import ems_aio.model.RoleBean;
import ems_aio.model.StaffBean;
import ems_aio.model.UserBean;
@Controller
public class StaffController {

	@Autowired
	private StaffService StaffService;
	@Autowired
	private RoleService RoleService;
	
//	@RequestMapping(value="/setupaddStaff" ,method=RequestMethod.GET)
//	public ModelAndView setupaddStaff() {
//		return new ModelAndView("EMS-STI-001","user",new UserBean());
//	}
	
	@RequestMapping(value="/setupaddStaff" ,method=RequestMethod.GET)
	public String addStaff( HttpSession session,ModelMap model ) {
		MROL001 dto = new MROL001();
		dto.setRolid("");
		dto.setRolname("");
		List<MROL001> list=RoleService.getAll();
		
		session.setAttribute("rolelist", list);
		model.addAttribute("bean", new StaffBean());
		return "EMS-STI-001";
	}
	
}
