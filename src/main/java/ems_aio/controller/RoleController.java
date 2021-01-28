package ems_aio.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ems_aio.dao.RoleService;
import ems_aio.dto.MROL001;
import ems_aio.model.QualifyBean;
import ems_aio.model.RoleBean;
import ems_aio.model.UserBean;

@Controller
public class RoleController {
	@Autowired
	private RoleService RoleService;

	@RequestMapping(value = "/displayrole", method = RequestMethod.GET)
	public ModelAndView displayrole(Model model) {
		List<MROL001> list;
		list = RoleService.getAll();
		RoleBean bean=new RoleBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-MSR-003", "rolelist", list);
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
	public String addrole(@ModelAttribute("bean") @Validated RoleBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSR-001";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MROL001 dto = new MROL001();
		dto.setRolid(bean.getId());
		dto.setRolname(bean.getName());
		dto.setCreatedate(now);
		dto.setUpdatedate(now);
		dto.setStatus(b);
		Optional<MROL001> chk = RoleService.getRoleByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddrole";
		}
		try {
			RoleService.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddrole";
		} catch (Exception e) {
			model.addAttribute("err", "Register fail");
			return "EMS-MSR-001";
		}
	}
	
	@RequestMapping(value = "/setuproleupdate", method = RequestMethod.GET)
	public ModelAndView setuproleupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MROL001> dtoget = RoleService.getRoleByCode(id);
		MROL001 dto1=dtoget.get();
		RoleBean rol = new RoleBean();
		rol.setId(dto1.getRolid());
		rol.setName(dto1.getRolname());
		rol.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSR-002", "bean", rol);

	}
	
	@RequestMapping(value = "/updaterole", method = RequestMethod.POST)
	public String updaterole(@ModelAttribute("bean") @Validated RoleBean bean, BindingResult bs, ModelMap model) {
		if (bs.hasErrors()) {
			return "EMS-MSR-002";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MROL001 dto = new MROL001();
		dto.setRolid(bean.getId()); 
		dto.setRolname(bean.getName());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		try {
			RoleService.update(dto, bean.getId());
			model.addAttribute("msg", "Update successful");
			return "EMS-MSR-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSR-002";
		}
	}
	
	@RequestMapping(value = "/roledelete", method = RequestMethod.GET)
	public String deleterole(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		Optional<MROL001> dtoget = RoleService.getRoleByCode(id);
		MROL001 dto=dtoget.get(); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		RoleService.update(dto, id);
		return "redirect:/displayrole";
	}

	@RequestMapping(value = "/rolesearch", method = RequestMethod.GET)
	public ModelAndView setupStudentSearch(@RequestParam(name = "message", required = false) String message,
			ModelMap model) {
		model.addAttribute("msg", message);
		return new ModelAndView("EMS-MSR-003", "bean", new RoleBean());
	}
	@RequestMapping(value = "/searchrole", method = RequestMethod.GET)
	public String displayView(@ModelAttribute("bean") RoleBean bean, ModelMap model) {
		
		List<MROL001> list;
		String i = bean.getId();
		if (i.equals("")) {
			list = RoleService.getAll();
		}else {
			 list = RoleService.getsearchrole(i);
		}
		System.out.println(list.size());
		if (list.size() == 0)
			model.addAttribute("msg", "Role not found!");
		else
			model.addAttribute("rolelist", list);
		//return "BUD001";
		return "EMS-MSR-003";
	}
}