package ems_aio.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ems_aio.dao.RoleService;
import ems_aio.dto.MROL001;
import ems_aio.model.RoleBean;
import ems_aio.model.UserBean;

@Controller
public class RoleController {
	@Autowired
	private RoleService RoleService;

	@RequestMapping(value = "/displayrole", method = RequestMethod.GET)
	public ModelAndView displayrole() {
		return new ModelAndView("EMS-MSR-003", "RoleBean", new RoleBean());
	}

	@RequestMapping(value = "/setupaddrole", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") RoleBean bean, ModelMap model) {
		MROL001 chk = RoleService.findLastID();
		int Intlast=0;
		String sf2;
		RoleBean rol=new RoleBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("ROL%03d", Intlast);
		} else {
			String StrID = chk.getRolid();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("ROL%03d", Intlast);
		}
		rol.setId(sf2);
		model.addAttribute("bean",rol);
		return new ModelAndView("EMS-MSR-001", "bean", rol);
	}

	@RequestMapping(value = "/addrole", method = RequestMethod.POST)
	public String addbook(@ModelAttribute("bean") @Validated RoleBean bean, BindingResult bs, ModelMap model) {
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		MROL001 dto = new MROL001();
		dto.setRolid(bean.getId());
		System.out.println(bean.getId());
		dto.setRolname(bean.getName());
		System.out.println(bean.getName());
		dto.setCreatedate(dtf.format(now));
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		RoleService.save(dto);
		return "index";

		/*
		 * if (!bean.getId().equals("") && !bean.getName().equals("") &&
		 * !bean.getStatus().equals("") && !bean.getClassName().equals("") &&
		 * !bean.getDay().equals("Day") && !bean.getMonth().equals("Month") &&
		 * !bean.getYear().equals("Year")) { Student dto = new Student();
		 * dto.setStudentId(bean.getId()); dto.setStudentName(bean.getName());
		 * dto.setStatus(bean.getStatus()); dto.setClassName(bean.getClassName());
		 * dto.setRegisterDate(bean.getYear() + "-" + bean.getMonth() + "-" +
		 * bean.getDay());
		 * 
		 * Student dtoc = new Student(); dtoc.setStudentId(bean.getId());
		 * Optional<Student> chk = StudentService.getStudentByCode(bean.getId()); if
		 * (chk.isPresent()) { model.addAttribute("err",
		 * "StudentId has been already exist!"); // return "redirect:/setupaddstudent";
		 * return "BUD002"; } else { try { StudentService.save(dto);
		 * model.addAttribute("msg", "Insert successful"); return
		 * "redirect:/displaystudent"; } catch (Exception e) { model.addAttribute("err",
		 * "Insert fail"); return "BUD002"; } } } else { model.addAttribute("err",
		 * "Fields must not be null"); return "BUD002"; }
		 */

	}

}
