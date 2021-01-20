package ems_aio.controller;

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
import ems_aio.dto.Role;
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
		return new ModelAndView("EMS-MSR-001", "bean", new RoleBean());
	}

	@RequestMapping(value = "/addroll", method = RequestMethod.POST)
	public String addbook(@ModelAttribute("bean") @Validated RoleBean bean, BindingResult bs, ModelMap model) {
		if (bs.hasErrors()) {
			return "/setupaddrole";
		}
		Role dto = new Role();
		dto.setRolid(null);
		dto.setRolname(null);
		dto.setCreatedate(null);
		dto.setUpdatedate(null);
		dto.setStatus(null);
		
		RoleService.save(dto);
		return "BUD002";
		
		/*if (!bean.getId().equals("") && !bean.getName().equals("") && !bean.getStatus().equals("")
				&& !bean.getClassName().equals("") && !bean.getDay().equals("Day") && !bean.getMonth().equals("Month")
				&& !bean.getYear().equals("Year")) {
			Student dto = new Student();
			dto.setStudentId(bean.getId());
			dto.setStudentName(bean.getName());
			dto.setStatus(bean.getStatus());
			dto.setClassName(bean.getClassName());
			dto.setRegisterDate(bean.getYear() + "-" + bean.getMonth() + "-" + bean.getDay());

			Student dtoc = new Student();
			dtoc.setStudentId(bean.getId());
			Optional<Student> chk = StudentService.getStudentByCode(bean.getId());// studentDao.selectAll(dtoc);
			if (chk.isPresent()) {
				model.addAttribute("err", "StudentId has been already exist!");
				// return "redirect:/setupaddstudent";
				return "BUD002";
			} else {
				try {
					StudentService.save(dto);
					model.addAttribute("msg", "Insert successful");
					return "redirect:/displaystudent";
				} catch (Exception e) {
					model.addAttribute("err", "Insert fail");
					return "BUD002";
				}
			}
		} else {
			model.addAttribute("err", "Fields must not be null");
			return "BUD002";
		}*/

	}

}
