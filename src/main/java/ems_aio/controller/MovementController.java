package ems_aio.controller;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ems_aio.dao.DepartmentService;
import ems_aio.dao.PositionService;
import ems_aio.dao.StaffService;
import ems_aio.dto.EmpMovDto;
import ems_aio.dto.EmpSalDto;
import ems_aio.dto.MCTF001;
import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.MROL001;
import ems_aio.dto.StaffDto;
import ems_aio.model.MovementBean;
import ems_aio.model.RoleBean;
import ems_aio.model.SalaryBean;
import ems_aio.model.StaffBean;
import ems_aio.model.UserBean;

@Controller

public class MovementController {
	@Autowired
	private DepartmentService DepartmentService;
	@Autowired
	private PositionService PositionService;
	@Autowired
	private ems_aio.dao.MovementService MovementService;
	@Autowired
	private StaffService StaffService;


	@RequestMapping(value = "/setupaddmovement", method = RequestMethod.GET)
	public ModelAndView setupsalary(@ModelAttribute("bean") StaffBean bean, ModelMap model,
			HttpServletRequest request) {
		EmpMovDto chk = MovementService.findLastID();
		int Intlast = 0;
		String sf2;
		MovementBean data = new MovementBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("MOV%03d", Intlast);
		} else {
			String StrID = chk.getMov_id();
			Intlast = Integer.parseInt(StrID.substring(3, 6)) + 1;
			sf2 = String.format("MOV%03d", Intlast);
		}
		data.setId(sf2);
		List<MDEP001> depList=DepartmentService.getAll();
		List<MPOS001> posList=PositionService.getAll();
		request.getSession().setAttribute("deplist", depList);
		request.getSession().setAttribute("poslist", posList);
		List<StaffDto> stflist = StaffService.getAll();
		request.getSession().setAttribute("stafflist", stflist);
		return new ModelAndView("EMS-STM-002", "bean", data);
	}
	
	@RequestMapping(value = "/addmovement", method = RequestMethod.POST)
	public String updaterole(@ModelAttribute("bean") @Validated SalaryBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-STM-002";
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Timestamp now = new Timestamp(date.getTime());
		EmpSalDto dto = new EmpSalDto();
		dto.setSal_id(bean.getId());

		dto.setSal_empid(bean.getSid());
		dto.setSal_dep(bean.getSaldep());
		dto.setSal_pos(bean.getSalpos());
		dto.setSal_salary(bean.getSalary());
		dto.setSal_admin((StaffService.getByCode("STF0001")).get());
		dto.setSal_date(java.sql.Date.valueOf(bean.getSaldate()));
		dto.setSal_create(now);

		/*Optional<EmpSalDto> chk = SalaryService.getByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddstaff";
		}
		try {
			SalaryService.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddsalary";
		} catch (Exception e) {
			model.addAttribute("err", "Register fail");
			return "EMS-PYR-001";
		}*/
		return "EMS-PYR-001";
	}
	
	/*
	@RequestMapping(value="/setupPayRollHistory" ,method=RequestMethod.GET)
	public ModelAndView setupPayRollHistory(Model model) {
		List<EmpSalDto> list;
		list=SalaryService.getAll();
		SalaryBean bean=new SalaryBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-PYR-003","salhis",list);
	}
	//salhis
	/*List<StaffDto> list;
		list = StaffService.getAll();
		StaffBean bean=new StaffBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-STI-003", "stafflist", list);*/

	@GetMapping(path = "/setupstaffid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffDto> courseName(@RequestParam(name = "id", required = true) String Id) {

		Optional<StaffDto> Option = StaffService.getByCode(Id);

		StaffDto course = Option.get();
		return new ResponseEntity<StaffDto>(course, HttpStatus.OK);
	}

}
