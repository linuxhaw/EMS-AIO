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
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.PathVariable;
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
//	
//	@RequestMapping(value = "/displaymovement", method = RequestMethod.GET)
//	public ModelAndView displayrole(Model model) {
//		List<EmpMovDto> list;
//		list = MovementService.getAll();
//		MovementBean bean=new MovementBean();
//		model.addAttribute("bean", bean);
//		return new ModelAndView("EMS-STM-003", "movelist", list);
//	}
	@GetMapping("/displaymovement/page/{pageNo}")
	public String displayMovementList(@PathVariable("pageNo")int pageNo,Model model) {
		int pageSize=4;
		MovementBean bean=new MovementBean();
		Page<EmpMovDto> page=MovementService.movementPagi(pageNo, pageSize);
		List<EmpMovDto> pagi=page.getContent();
		model.addAttribute("bean",bean);
		model.addAttribute("movementlist",pagi);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);
		return "EMS-STM-003";
		
	}
	@GetMapping("/displaymovement/searchpage/{pageNo}")
	public String displaySerachMovement(@PathVariable("pageNo")int pageNo,@Param("id")String id,Model model) {
		int pageSize=4;
		MovementBean bean=new 	MovementBean();
		model.addAttribute("id",id);
		bean.setId(id);
		model.addAttribute("bean", bean);
		Page<EmpMovDto> page=MovementService.movementSearchPagi(id,pageNo, pageSize);
		List<EmpMovDto> list=page.getContent();

		if(id.equals("")) {
			model.addAttribute("msg","Please Enter data to search!");
			return "redirect:/displaymovement";
		}
		if(list.size()==0) {
			model.addAttribute("msg", " DATA  NOT  FOUND!");
			return "EMS-STM-003";
		}
		else {
		model.addAttribute("movementlist",list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);}
		return "EMS-STM-003";
		
		
	}
	@GetMapping("/displaymovement")
	public String displayMovement(@ModelAttribute("bean")MovementBean bean,Model model) {
		String id=bean.getId();
		if(id!=null) {
		model.addAttribute("id",id);
		return displaySerachMovement(1,id, model);}
		else {
			return displayMovementList(1,model);
		}
	}

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
	public String updaterole(@ModelAttribute("bean") @Validated MovementBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-STM-002";
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Timestamp now = new Timestamp(date.getTime());
		EmpMovDto dto = new EmpMovDto();		
		String process=bean.getProcess();
		if (process!=null) {
			dto.setMov_id(bean.getId());
			dto.setMov_empid(bean.getSid());
			dto.setMov_admin((StaffService.getByCode("STF0001")).get());
			dto.setMov_remark(bean.getRemark());
			dto.setMov_create(now);
			StaffDto staff = StaffService.getByCode(bean.getSid().getEmp_id()).get();
			if (process.equals("promotion") || process.equals("demotion")) {
				dto.setMov_pos(bean.getPos());
				dto.setMov_dep(staff.getEmp_dep());
				dto.setMov_process(bean.getProcess());				
				staff.setEmp_pos(bean.getPos());
			}else if (process.equals("transfer")) {
				dto.setMov_pos(bean.getPos());
				dto.setMov_dep(bean.getDep());
				dto.setMov_process(bean.getProcess());	
				staff.setEmp_pos(bean.getPos());
				staff.setEmp_dep(bean.getDep());
				
			}else if (process.equals("resignation")) {
				dto.setMov_process(bean.getProcess());	
				staff.setEmp_status(false);
			}else {
				dto.setMov_process(bean.getProcess());	
				staff.setEmp_status(false);
				staff.setEmp_blacklist(true);
			}
			try {
				staff.setEmp_update(now);
				StaffService.update(staff, staff.getEmp_id());

			} catch (Exception e) {
				System.out.println(e.toString());
				return "EMS-STM-002";
			}
			Optional<EmpMovDto> chk = MovementService.getByCode(bean.getId());
			if (chk.isPresent()) {
				redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
				return "redirect:/setupaddmovement";
			}
			try {
				MovementService.save(dto);
				redirAttrs.addFlashAttribute("msg", "Register successful");
				return "redirect:/setupaddsalary";
			} catch (Exception e) {
				model.addAttribute("err", "Register fail");
				return "EMS-STM-002";
			}
		}else {
			return "redirect:/setupaddmovement";
		}

	}
	
	

	@GetMapping(path = "/setupstaffid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StaffDto> courseName(@RequestParam(name = "id", required = true) String Id) {

		Optional<StaffDto> Option = StaffService.getByCode(Id);

		StaffDto course = Option.get();
		return new ResponseEntity<StaffDto>(course, HttpStatus.OK);
	}

	
//	@RequestMapping(value="/setupRepoetBlackList" ,method=RequestMethod.GET)
//	public ModelAndView setupRepoetBlackList(Model model) {
//		List<EmpMovDto> list;
//		list = MovementService.getBlackList();
//		StaffBean bean=new StaffBean();
//		model.addAttribute("bean", bean);
//		//return new ModelAndView("EMS-STI-003", "blacklist", list);
//		return new ModelAndView("EMS-ARB-003","blacklist",list);
//	}
	@GetMapping("/setupReportBlackList/page/{pageNo}")
	public String displayBlackListReport(@PathVariable("pageNo")int pageNo,Model model) {
		int pageSize=4;
	StaffBean bean=new StaffBean();
		Page<EmpMovDto> page=MovementService.blacklist(pageNo, pageSize);
		List<EmpMovDto> pagi=page.getContent();
		model.addAttribute("bean",bean);
		model.addAttribute("blacklist",pagi);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);
		return "EMS-ARB-003";
		
	}
	@GetMapping("/setupReportBlackList/searchpage/{pageNo}")
	public String displaySearchBlackList(@PathVariable("pageNo")int pageNo,@Param("id")String id,Model model) {
		int pageSize=4;
		StaffBean bean=new StaffBean();
		model.addAttribute("id",id);
		bean.setId(id);
		model.addAttribute("bean", bean);
		Page<EmpMovDto> page=MovementService.blacklistSearch(id,pageNo, pageSize);
		List<EmpMovDto> list=page.getContent();

		if(id.equals("")) {
			model.addAttribute("msg","Please Enter data to search!");
			return "redirect:/setupReportBlackList";
		}
		if(list.size()==0) {
			model.addAttribute("msg", " DATA  NOT  FOUND!");
			return "EMS-ARB-003";
		}
		else {
			model.addAttribute("blacklist",list);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalElements",page.getTotalElements());
			model.addAttribute("currentPage",pageNo);
			return "EMS-ARB-003";
		}
	}
	@GetMapping("/setupReportBlackList")
	public String displayBlacklist(@ModelAttribute("bean")MovementBean bean,Model model) {
		String id=bean.getId();
		if(id!=null) {
		model.addAttribute("id",id);
		return displaySearchBlackList(1,id, model);}
		else {
			return displayBlackListReport(1,model);
		}
	}
}
