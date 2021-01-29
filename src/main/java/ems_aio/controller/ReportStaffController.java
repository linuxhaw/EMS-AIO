package ems_aio.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import ems_aio.dao.BankService;
import ems_aio.dao.StaffService;
import ems_aio.dao.RoleService;
import ems_aio.dao.DepartmentService;
import ems_aio.dao.PositionService;
import ems_aio.dto.MBNK001;
import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.MROL001;
import ems_aio.dto.StaffDto;
import ems_aio.model.BankBean;
import ems_aio.model.DepartmentBean;
import ems_aio.model.PositionBean;
import ems_aio.model.RoleBean;
import ems_aio.model.StaffBean;
import ems_aio.model.UserBean;

@Controller
public class ReportStaffController {
	@Autowired
	private StaffService StaffService;
	@Autowired
	private BankService BankService;
	@Autowired
	private RoleService RoleService;
	@Autowired
	private DepartmentService DepartmentService;
	@Autowired
	private PositionService PositionService;
	
	@RequestMapping(value = "/setupReportStaff", method = RequestMethod.GET)
	public ModelAndView displaystaffreport(Model model) {
		List<StaffDto> list;
		list = StaffService.getAll();
		StaffBean bean=new StaffBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-ARS-003", "stafflist", list);
		
	}
	

	
	
	@RequestMapping(value = "/staffdetail", method = RequestMethod.GET)
	public ModelAndView staffdetail(@RequestParam("id")String id, ModelMap model) {
		return new ModelAndView("EMS-ARS-004","user",new UserBean());
		/*
		 * Optional<MEMP001> dtoget = StaffService.getStaffByCode(id); MEMP001
		 * dto1=dtoget.get(); StaffBean rol = new StaffBean(); rol.setId(dto1.get);
		 * rol.setName(dto1.getRolname()); rol.setCreate(dto1.getCreatedate()); return
		 * new ModelAndView("EMS-ARS-003", "bean", rol);
		 */
	}
	 
	
	
	
	
}