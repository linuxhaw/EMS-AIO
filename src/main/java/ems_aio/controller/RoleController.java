package ems_aio.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		List<MROL001> list;
		list = RoleService.getAll();
		System.out.println(list.size());
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
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		MROL001 dto = new MROL001();
		dto.setRolid(bean.getId()); 
		dto.setRolname(bean.getName());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		RoleService.update(dto, bean.getId());
		return "index";
	}
	
	@RequestMapping(value = "/roledelete", method = RequestMethod.GET)
	public String deleterole(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Optional<MROL001> dtoget = RoleService.getRoleByCode(id);
		MROL001 dto=dtoget.get(); 
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		RoleService.update(dto, id);
		return "index";
	}
}
