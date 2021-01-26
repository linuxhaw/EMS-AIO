package ems_aio.controller;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



import ems_aio.dto.Position;
import ems_aio.model.PositionBean;
import ems_aio.service.Position_Service;
=======
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

import ems_aio.dao.PositionService;
import ems_aio.dto.MPOS001;
import ems_aio.dto.MROL001;
import ems_aio.model.PositionBean;
>>>>>>> 657aeebe99d3ab8c3ac0d40650c70a579fa2e4a0

@Controller
public class PositionController {
	@Autowired
<<<<<<< HEAD
	Position_Service service;
@GetMapping("/admin/position")
public String position(Position position ,ModelMap model) {
	List<Position> list=service.getAll();
	model.addAttribute("pList",list);
	return "EMS-MSP-003";
	
	
}
@GetMapping("/admin/addPosition")
public ModelAndView addPosition(ModelMap model) {
	int size=service.getAll().size()+1;
	if(size<10) {
		model.addAttribute("id","POS00"+size);
	}
	else if(size<100){
		model.addAttribute("id", "POS0"+size);
	}
	else if(size<1000){
		model.addAttribute("id", "POS"+size);
	}
	
	return new ModelAndView("EMS-MSP-001","position",new PositionBean());
}

@PostMapping("/admin/addedPosition")
public String addedPosition(@ModelAttribute("position")@Validated Position position,BindingResult binding,ModelMap model) {
	if(binding.hasErrors()) {
		model.addAttribute("position",position);
		return "EMS-MSP-001";
	}
PositionBean pBean=new PositionBean();
position.setpId(pBean.getpId());
position.setpName(pBean.getpName());
service.insert(position);
model.addAttribute("success","Successfully Saved Data!");
	return "EMS-MSP-001";
	
}}
=======
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
		LocalDateTime now = LocalDateTime.now();
		MPOS001 dto = new MPOS001();
		dto.setPosid(bean.getId());
		dto.setPosname(bean.getName());
		dto.setCreatedate(dtf.format(now));
		dto.setUpdatedate(dtf.format(now));
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
		LocalDateTime now = LocalDateTime.now();
		MPOS001 dto = new MPOS001();
		dto.setPosid(bean.getId()); 
		dto.setPosname(bean.getName());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(dtf.format(now));
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
		LocalDateTime now = LocalDateTime.now();
		Optional<MPOS001> dtoget = serv.getPositionByCode(id);
		MPOS001 dto=dtoget.get(); 
		dto.setUpdatedate(dtf.format(now));
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
>>>>>>> 657aeebe99d3ab8c3ac0d40650c70a579fa2e4a0
