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

import ems_aio.dao.PositionService;
import ems_aio.dto.MPOS001;
import ems_aio.dto.MROL001;
import ems_aio.model.PositionBean;

@Controller
public class PositionController {
	@Autowired
	private PositionService serv;

	@RequestMapping(value = "/displayposition", method = RequestMethod.GET)
	public ModelAndView displayPosition(Model model) {
		List<MPOS001> list;
		list = serv.getAll();
		System.out.println(list.size());
		PositionBean bean=new PositionBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-MSP-003", "positionlist", list);
	}

	@RequestMapping(value = "/setupaddposition", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") PositionBean bean, ModelMap model) {
		MPOS001 chk = serv.findLastID();
		int Intlast=0;
		String sf2;
		PositionBean bea=new PositionBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("POS%03d", Intlast);
		} else {
			String StrID = chk.getPosid();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("POS%03d", Intlast);
		}
		bea.setId(sf2);
		model.addAttribute("bean",bea);
		return new ModelAndView("EMS-MSP-001", "bean", bea);
	}

	@RequestMapping(value = "/addposition", method = RequestMethod.POST)
	public String addposition(@ModelAttribute("bean") @Validated PositionBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSP-001";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MPOS001 dto = new MPOS001();
		dto.setPosid(bean.getId());
		dto.setPosname(bean.getName());
		dto.setCreatedate(now);
		dto.setUpdatedate(now);
		dto.setStatus(b);
		Optional<MPOS001> chk = serv.getPositionByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddposition";
		}
		try {
			serv.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddposition";
		} catch (Exception e) {
			model.addAttribute("err", "Register fail");
			return "EMS-MSP-001";
		}
	}
	
	@RequestMapping(value = "/setuppositionupdate", method = RequestMethod.GET)
	public ModelAndView setuppositionupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MPOS001> dtoget = serv.getPositionByCode(id);
		MPOS001 dto1=dtoget.get();
		PositionBean bean = new PositionBean();
		bean.setId(dto1.getPosid());
		bean.setName(dto1.getPosname());
		bean.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSP-002", "bean", bean);

	}
	
	@RequestMapping(value = "/updateposition", method = RequestMethod.POST)
	public String updateposition(@ModelAttribute("bean") @Validated PositionBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSP-002";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MPOS001 dto = new MPOS001();
		dto.setPosid(bean.getId()); 
		dto.setPosname(bean.getName());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		
		try {
			serv.update(dto, bean.getId());
			model.addAttribute("err", "Update successful");
			return "EMS-MSP-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSP-002";
		}
	}
	
	@RequestMapping(value = "/positiondelete", method = RequestMethod.GET)
	public String deleteposition(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		Optional<MPOS001> dtoget = serv.getPositionByCode(id);
		MPOS001 dto=dtoget.get(); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		serv.update(dto, id);
		return "redirect:/displayposition";
	}

	@RequestMapping(value = "/searchposition", method = RequestMethod.GET)
	public String displayView(@ModelAttribute("bean") PositionBean bean, ModelMap model) {
		
		List<MPOS001> list;
		String i = bean.getId();
		if (i.equals("")) {
			list = serv.getAll();
		}else {
			 list = serv.getsearchPosition(i);
		}
		System.out.println(list.size());
		if (list.size() == 0)
			model.addAttribute("msg", "User not found!");
		else
			model.addAttribute("positionlist", list);
		//return "BUD001";
		return "EMS-MSP-003";
	}
}
