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

import ems_aio.dto.User;
import ems_aio.model.UserBean;
import ems_aio.service.Login_Service;




@Controller
public class LoginController {
	@Autowired
	Login_Service service;
	
@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("user", new UserBean());
		return "EMS-LGN-001";
	}
	@RequestMapping(value="/dashboard", method=RequestMethod.POST)	
	public String login(@ModelAttribute("user") UserBean bean ,BindingResult binding,Model model,HttpSession session) {

//		if(binding.hasErrors()) {
//			model.addAttribute("user", bean);
//			return "EMS-LGN-001";
//		}
//	for(User u:user) {
//		
//	
//	if(u.getId().equals(bean.getId())&&u.getPassword(kk).equals(bean.getPassword())) {
//	session.setAttribute("lgn", u.getName());
//	return "welcome";
//	}
//	else {
//		model.addAttribute("error","User Not Found!");
//		return "EMS-LGN-001";
//	}}
//	return "welcome";
//	}
		
			User dto=new User();
			dto.setId(bean.getId());
			
			List<User> list = service.getAllUser();
			if (list.size() == 0) {
				model.addAttribute("err", "User not found!");
				return "EMS-LGN-001";
			}
			else if (dto.getId().equals(list.get(0).getId())&&bean.getPassword().equals(list.get(0).getPassword())) {
				
					return "welcome2";
				}
			
			
			else {
				model.addAttribute("err", "Input is incorrect!");
				return "EMS-LGN-001";
			}
			
		}
	}
	

