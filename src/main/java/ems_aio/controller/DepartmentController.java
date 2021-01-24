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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ems_aio.dao.DepartmentService;
import ems_aio.dto.MDEP001;
import ems_aio.model.DepartmentBean;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService serv;

	@RequestMapping(value = "/displaydepartment", method = RequestMethod.GET)
	public ModelAndView displayQualification(Model model) {
		List<MDEP001> list;
		list = serv.getAll();
		DepartmentBean bean=new DepartmentBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-MSD-003", "departmentlist", list);
	}
	
	@RequestMapping(value = "/setupadddepartment", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") DepartmentBean bean, ModelMap model) {
		MDEP001 chk = serv.findLastID();
		int Intlast=0;
		String sf2;
		DepartmentBean bea=new DepartmentBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("DEP%03d", Intlast);
		} else {
			String StrID = chk.getId();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("DEP%03d", Intlast);
		}
		bea.setId(sf2);
		model.addAttribute("bean",bea);
		return new ModelAndView("EMS-MSD-001", "bean", bea);
	}

	@RequestMapping(value = "/adddepartment", method = RequestMethod.POST)
	public String addposition(@ModelAttribute("bean") @Validated DepartmentBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSD-001";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		MDEP001 dto = new MDEP001();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setLoc(bean.getLoc());
		dto.setHead(bean.getHead());
		dto.setCreatedate(dtf.format(now));
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		Optional<MDEP001> chk = serv.getByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupadddepartment";
		}
		try {
			serv.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupadddepartment";
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("err", "Register fail");
			return "EMS-MSD-001";
		}
	}
	
	@RequestMapping(value = "/setupdepartmentupdate", method = RequestMethod.GET)
	public ModelAndView setuppositionupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MDEP001> dtoget = serv.getByCode(id);
		MDEP001 dto1=dtoget.get();
		DepartmentBean bean = new DepartmentBean();
		bean.setId(dto1.getId());
		bean.setName(dto1.getName());
		bean.setLoc(dto1.getLoc());
		bean.setHead(dto1.getHead());
		bean.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSD-002", "bean", bean);

	}
	
	@RequestMapping(value = "/updatedepartment", method = RequestMethod.POST)
	public String updateposition(@ModelAttribute("bean") @Validated DepartmentBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSD-002";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		MDEP001 dto = new MDEP001();
		dto.setId(bean.getId()); 
		dto.setName(bean.getName());
		bean.setLoc(bean.getLoc());
		bean.setHead(bean.getHead());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		try {
			serv.update(dto, bean.getId());
			model.addAttribute("msg", "Update successful");
			return "EMS-MSD-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSD-002";
		}
	}
	
	@RequestMapping(value = "/departmentdelete", method = RequestMethod.GET)
	public String deleteposition(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Optional<MDEP001> dtoget = serv.getByCode(id);
		MDEP001 dto=dtoget.get(); 
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		serv.update(dto, id);
		return "redirect:/displaydepartment";
	}

	@RequestMapping(value = "/searchdepartment", method = RequestMethod.GET)
	public String displayView(@ModelAttribute("bean") DepartmentBean bean, ModelMap model) {
		
		List<MDEP001> list;
		String i = bean.getId();
		if (i.equals("")) {
			list = serv.getAll();
		}else {
			 list = serv.getsearch(i);
		}
		if (list.size() == 0)
			model.addAttribute("msg", "Certification not found!");
		else
			model.addAttribute("departmentlist", list);
		//return "BUD001";
		return "EMS-MSD-003";
	}
}