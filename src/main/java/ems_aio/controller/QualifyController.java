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

import ems_aio.dao.QualifyService;
import ems_aio.dto.MPOS001;
import ems_aio.dto.MQUL001;
import ems_aio.model.PositionBean;
import ems_aio.model.QualifyBean;

@Controller
public class QualifyController {
	@Autowired
	private QualifyService serv;

	@RequestMapping(value = "/displayqualify", method = RequestMethod.GET)
	public ModelAndView displayQualification(Model model) {
		List<MQUL001> list;
		list = serv.getAll();
		QualifyBean bean=new QualifyBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-MSQ-003", "qualifylist", list);
	}
	
	@RequestMapping(value = "/setupaddqualify", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") QualifyBean bean, ModelMap model) {
		MQUL001 chk = serv.findLastID();
		int Intlast=0;
		String sf2;
		QualifyBean bea=new QualifyBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("QUL%03d", Intlast);
		} else {
			String StrID = chk.getQulid();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("QUL%03d", Intlast);
		}
		bea.setId(sf2);
		model.addAttribute("bean",bea);
		return new ModelAndView("EMS-MSQ-001", "bean", bea);
	}

	@RequestMapping(value = "/addqualify", method = RequestMethod.POST)
	public String addposition(@ModelAttribute("bean") @Validated QualifyBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSQ-001";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MQUL001 dto = new MQUL001();
		dto.setQulid(bean.getId());
		dto.setQulname(bean.getName());
		dto.setQulschool(bean.getSchool());
		dto.setCreatedate(now);
		dto.setUpdatedate(now);
		dto.setStatus(b);
		Optional<MQUL001> chk = serv.getByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddqualify";
		}
		try {
			serv.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddqualify";
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("err", "Register fail");
			return "EMS-MSQ-001";
		}
	}
	
	@RequestMapping(value = "/setupqualifyupdate", method = RequestMethod.GET)
	public ModelAndView setuppositionupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MQUL001> dtoget = serv.getByCode(id);
		MQUL001 dto1=dtoget.get();
		QualifyBean bean = new QualifyBean();
		bean.setId(dto1.getQulid());
		bean.setName(dto1.getQulname());
		bean.setSchool(dto1.getQulschool());
		bean.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSQ-002", "bean", bean);

	}
	
	@RequestMapping(value = "/updatequalify", method = RequestMethod.POST)
	public String updateposition(@ModelAttribute("bean") @Validated QualifyBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSQ-002";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MQUL001 dto = new MQUL001();
		dto.setQulid(bean.getId()); 
		dto.setQulname(bean.getName());
		dto.setQulschool(bean.getSchool());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		try {
			serv.update(dto, bean.getId());
			model.addAttribute("msg", "Update successful");
			return "EMS-MSQ-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSQ-002";
		}
	}
	
	@RequestMapping(value = "/qualifydelete", method = RequestMethod.GET)
	public String deleteposition(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		Optional<MQUL001> dtoget = serv.getByCode(id);
		MQUL001 dto=dtoget.get(); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		serv.update(dto, id);
		return "redirect:/displayqualify";
	}

	@RequestMapping(value = "/qualifysearch", method = RequestMethod.GET)
	public ModelAndView setupStudentSearch(@RequestParam(name = "message", required = false) String message,
			ModelMap model) {
		model.addAttribute("msg", message);
		return new ModelAndView("EMS-MSQ-003", "bean", new QualifyBean());
	}
	@RequestMapping(value = "/searchqualify", method = RequestMethod.GET)
	public String displayView(@ModelAttribute("bean") QualifyBean bean, ModelMap model) {
		
		List<MQUL001> list;
		String i = bean.getId();
		if (i.equals("")) {
			list = serv.getAll();
		}else {
			 list = serv.getsearch(i);
		}
		if (list.size() == 0)
			model.addAttribute("msg", "Qualification not found!");
		else
			model.addAttribute("qualifylist", list);
		//return "BUD001";
		return "EMS-MSQ-003";
	}
}
