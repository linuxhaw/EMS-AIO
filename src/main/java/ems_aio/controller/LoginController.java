package ems_aio.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ems_aio.model.UserBean;




@Controller
public class LoginController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("user", new UserBean());
		return "index";
	}
	
	
	@RequestMapping(value = "/EMS-DSH-001.html", method = RequestMethod.GET)
	public String Dashboard(Model model) {
		model.addAttribute("user", new UserBean());
		return "EMS-DSH-001";
	}
	
	@RequestMapping(value = "/EMS-MSP-003.html", method = RequestMethod.GET)
	public String ShowPositions(Model model) {
		model.addAttribute("user", new UserBean());
		return "EMS-MSP-003";
	}
	
}
