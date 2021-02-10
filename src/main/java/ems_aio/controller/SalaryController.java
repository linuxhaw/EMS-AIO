package ems_aio.controller;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import ems_aio.dao.SalaryService;
import ems_aio.dao.StaffService;
import ems_aio.dto.EmployeeSalaryDto;
import ems_aio.dto.MPOS001;
import ems_aio.dto.MROL001;
import ems_aio.dto.StaffDto;
import ems_aio.model.RoleBean;
import ems_aio.model.SalaryBean;
import ems_aio.model.StaffBean;

@Controller

public class SalaryController {

	@Autowired
	private SalaryService SalaryService;
	@Autowired
	private StaffService StaffService;

	@RequestMapping(value = "/displaysalary", method = RequestMethod.GET)
	public ModelAndView displaysalary(Model model) {
		List<EmployeeSalaryDto> list;
		list = SalaryService.getAll();
		SalaryBean bean = new SalaryBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-PYR-003", "salarylist", list);
	}
	

	@RequestMapping(value = "/setupaddsalary", method = RequestMethod.GET)
	public String setupsalary(@ModelAttribute("bean") StaffBean bean, ModelMap model, HttpServletRequest request) {

		List<StaffDto> stflist = StaffService.getAll();
		request.getSession().setAttribute("stafflist", stflist);
		return "EMS-PYR-001";
	}
	
	/*
	 * @RequestMapping(value = "/setupstaffupdateid", method = RequestMethod.POST)
	 * public String updaterole(@ModelAttribute("bean") @Validated RoleBean bean,
	 * BindingResult bs, ModelMap model) { if (bs.hasErrors()) { return
	 * "EMS-MSR-002"; } boolean b = true; DateTimeFormatter dtf =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); Date date=new Date();
	 * Timestamp now=new Timestamp(date.getTime()); MROL001 dto = new MROL001();
	 * dto.setRolid(bean.getId()); dto.setRolname(bean.getName());
	 * dto.setCreatedate(bean.getCreate()); dto.setUpdatedate(now);
	 * dto.setStatus(b); try { RoleService.update(dto, bean.getId());
	 * model.addAttribute("msg", "Update successful"); return "EMS-MSR-002"; } catch
	 * (Exception e) { model.addAttribute("err", "Update fail"); return
	 * "EMS-MSR-002"; } }
	 */
	
	/*@RequestMapping(value = "/setupstaffsalaryid", method = RequestMethod.GET)
	public String setuproleupdate(@RequestParam("id")String id, ModelMap model,HttpServletRequest request) {
		//Optional<StaffDto> dtoget = StaffService.getByCode(id);
		//StaffDto dto1=dtoget.get();
		*
		 * StaffBean data = new StaffBean(); data.setName(dto1.getEmp_name());
		 * data.setDepartment(dto1.getEmp_pos()); data.setCreate(dto1.getCreatedate());
		 *
		//return new ModelAndView("EMS-PYR-001", "bean", dto1);
		return "EMS-PRY-001";

	}*/
	
	
	@GetMapping(path = "/setupstaffsalaryid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffDto> courseName(@RequestParam(name = "id", required = true) String Id) {
		
		Optional<StaffDto> Option = StaffService.getByCode(Id);
		
		StaffDto course = Option.get();
		System.out.println(course.getEmp_id());
		return new ResponseEntity<StaffDto>(course, HttpStatus.OK);
	}
	
}
