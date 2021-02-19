package ems_aio.controller;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ems_aio.dao.BankService;
import ems_aio.dao.DepartmentService;
import ems_aio.dao.StaffService;
import ems_aio.dto.MBNK001;
import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.StaffDto;
import ems_aio.model.BankBean;
import ems_aio.model.CertifyBean;
import ems_aio.model.PositionBean;
import ems_aio.model.UserBean;

@Controller
public class LoginController {
	@Autowired
	StaffService service;
	@Autowired
	private DepartmentService DepartmentService;

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("bean", new UserBean());
		return "EMS-LGN-001";
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("login") UserBean bean, HttpSession session, ModelMap model,RedirectAttributes redirAttrs) {
		String link;
		Optional<StaffDto> login = service.getByCode(bean.getId());
		if (login.isPresent()) {
			StaffDto check = login.get();
			if (bean.getPassword().equals(check.getEmp_password())) {
				if (check.getEmp_rol().getRolid().equals("ROL001")) {
					link = "redirect:/admindash";
				} else if (check.getEmp_rol().getRolid().equals("ROL002")) {
					
					link = "redirect:/ManagerProfile";

				} else {
					link = "redirect:/StaffProfile";
				}
				session.setAttribute("sesUser", check);
			} else {
				redirAttrs.addFlashAttribute("msg", "Your password is incorrect!");
				return "redirect:/";
				
			}
		}else if(bean.getId().equals("root")){
			StaffDto check = new StaffDto();
			check.setEmp_id("emp001");
			session.setAttribute("sesUser", check);
			return "redirect:/admindash";
		}
		else {
			redirAttrs.addFlashAttribute("msg", "User not found!");
			return "redirect:/";
		}
		return link;
	}
	
	@GetMapping("/admindash")
	public String admindash(Model model) {
		List<StaffDto> stflist=service.getAll();
		model.addAttribute("stf",stflist.size());
		List<MDEP001> deplist=DepartmentService.getAll();
		model.addAttribute("dep",deplist.size());
		List<StaffDto> stflast=service.getLatest();
		model.addAttribute("stflast",stflast);
		return "EMS-DSH-001";
		
	}
	
	/*@GetMapping("/displaybank")
	public String displayBank(@ModelAttribute("bean")BankBean bean,Model model) {
		String id=bean.getId();
		if(id!=null) {
		model.addAttribute("id",id);
		return bankPagi(1,id, model);}
		else {
			return bankPagiQuery(1,model);
		}
	}*/
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletRequest response) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}


}
