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

import ems_aio.dao.CertifyService;
import ems_aio.dto.MCTF001;
import ems_aio.model.CertifyBean;

@Controller
public class CertifyController {
	@Autowired
	private CertifyService serv;

	@RequestMapping(value = "/displaycertify", method = RequestMethod.GET)
	public ModelAndView displayQualification(Model model) {
		List<MCTF001> list;
		list = serv.getAll();
		CertifyBean bean=new CertifyBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-MSC-003", "certifylist", list);
	}
	
	@RequestMapping(value = "/setupaddcertify", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") CertifyBean bean, ModelMap model) {
		MCTF001 chk = serv.findLastID();
		int Intlast=0;
		String sf2;
		CertifyBean bea=new CertifyBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("CTF%03d", Intlast);
		} else {
			String StrID = chk.getId();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("CTF%03d", Intlast);
		}
		bea.setId(sf2);
		model.addAttribute("bean",bea);
		return new ModelAndView("EMS-MSC-001", "bean", bea);
	}

	@RequestMapping(value = "/addcertify", method = RequestMethod.POST)
	public String addposition(@ModelAttribute("bean") @Validated CertifyBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSC-001";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		MCTF001 dto = new MCTF001();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setSchool(bean.getSchool());
		dto.setCreatedate(dtf.format(now));
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		Optional<MCTF001> chk = serv.getByCode(bean.getId());
		System.out.println("Hell");
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddcertify";
		}
		try {
			serv.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddcertify";
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("err", "Register fail");
			return "EMS-MSC-001";
		}
	}
	
	@RequestMapping(value = "/setupcertifyupdate", method = RequestMethod.GET)
	public ModelAndView setuppositionupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MCTF001> dtoget = serv.getByCode(id);
		MCTF001 dto1=dtoget.get();
		CertifyBean bean = new CertifyBean();
		bean.setId(dto1.getId());
		bean.setName(dto1.getName());
		bean.setSchool(dto1.getSchool());
		bean.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSC-002", "bean", bean);

	}
	
	@RequestMapping(value = "/updatecertify", method = RequestMethod.POST)
	public String updateposition(@ModelAttribute("bean") @Validated CertifyBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSC-002";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		MCTF001 dto = new MCTF001();
		dto.setId(bean.getId()); 
		dto.setName(bean.getName());
		dto.setSchool(bean.getSchool());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		try {
			serv.update(dto, bean.getId());
			model.addAttribute("msg", "Update successful");
			return "EMS-MSC-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSC-002";
		}
	}
	
	@RequestMapping(value = "/certifydelete", method = RequestMethod.GET)
	public String deleteposition(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Optional<MCTF001> dtoget = serv.getByCode(id);
		MCTF001 dto=dtoget.get(); 
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		serv.update(dto, id);
		return "redirect:/displaycertify";
	}

	@RequestMapping(value = "/searchcertify", method = RequestMethod.GET)
	public String displayView(@ModelAttribute("bean") CertifyBean bean, ModelMap model) {
		
		List<MCTF001> list;
		String i = bean.getId();
		if (i.equals("")) {
			list = serv.getAll();
		}else {
			 list = serv.getsearch(i);
		}
		if (list.size() == 0)
			model.addAttribute("msg", "Certification not found!");
		else
			model.addAttribute("certifylist", list);
		//return "BUD001";
		return "EMS-MSC-003";
	}
}
