package ems_aio.controller;

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

@Controller
public class PositionController {
	@Autowired
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
