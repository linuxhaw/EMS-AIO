package ems_aio.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ems_aio.dao.MovementService;
import ems_aio.dao.SalaryService;
import ems_aio.dao.StaffService;
import ems_aio.dto.EmpMovDto;
import ems_aio.dto.EmpSalDto;
import ems_aio.dto.MCTF001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.MQUL001;
import ems_aio.dto.StaffDto;
import ems_aio.model.DateBean;
import ems_aio.model.MovementBean;
import ems_aio.model.PositionBean;
import ems_aio.model.SalaryBean;
import ems_aio.model.UserBean;

@Controller
public class StaffViewController {
	@Autowired
	private StaffService StaffService;
	@Autowired
	private MovementService MovementService;
	@Autowired
	private SalaryService SalaryService;

	@RequestMapping(value = "/StaffProfile", method = RequestMethod.GET)
	public ModelAndView StaffProfile(HttpSession session,Model model) {
		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
		StaffDto info = StaffService.getByCode(staff.getEmp_id()).get();
		Set<MCTF001> ctf=info.getCtf();
		model.addAttribute("ctf",ctf);
		Set<MQUL001> qul=info.getQul();
		model.addAttribute("qul",qul);
		return new ModelAndView("EMS-SRP-003", "user", new UserBean());
	}

	@GetMapping("/StaffMovement/searchpage/{pageNo}")
	public String displaySerachMovement(@PathVariable("pageNo") int pageNo, @Param("id") String search,
			HttpSession session, Model model) {
		int pageSize = 6;
		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
		String id = staff.getEmp_id();
		UserBean bean = new UserBean();
		bean.setId(search);
		model.addAttribute("bean", bean);
		Page<EmpMovDto> page = MovementService.movementStaffSearchPagi(search, id, pageNo, pageSize);
		List<EmpMovDto> list = page.getContent();

		if (id.equals("")) {
			model.addAttribute("msg", "Please Enter data to search!");
			return "redirect:/StaffMovement";
		}
		if (list.size() == 0) {
			model.addAttribute("msg", " DATA  NOT  FOUND!");
			return "EMS-SRM-003";
		} else {
			model.addAttribute("movlist", list);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalElements", page.getTotalElements());
			model.addAttribute("currentPage", pageNo);
		}
		return "EMS-SRM-003";

	}

	@GetMapping("/StaffMovement/page/{pageNo}")
	public String displayStaffMovement(@PathVariable("pageNo") int pageNo, HttpSession session, Model model) {
		int pageSize = 6;
		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
		String id = staff.getEmp_id();

		/*
		 * UserBean bean = new UserBean(); bean.setId(id); model.addAttribute("bean",
		 * bean);
		 */

		Page<EmpMovDto> page = MovementService.movementStaffPagi(id, pageNo, pageSize);
		List<EmpMovDto> list = page.getContent();
		model.addAttribute("movlist", list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("currentPage", pageNo);

		return "EMS-SRM-003";

	}

	@GetMapping("/StaffMovement")
	public String displayMovement(@ModelAttribute("bean") MovementBean bean, HttpSession session, Model model) {
		String id = bean.getId();
		if (id != null) {
			return displaySerachMovement(1, id, session, model);
		} else {

			return displayStaffMovement(1, session, model);
		}
	}

//	@RequestMapping(value="/StaffSalary" ,method=RequestMethod.GET)
//	public ModelAndView StaffSalary(HttpSession session,Model model) {
//		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
//		String id =staff.getEmp_id();
//		List<EmpSalDto> sallist=SalaryService.getStaffSal(id); 
//		System.out.println(sallist.size());
//		model.addAttribute("sallist",sallist);
//		return new ModelAndView("EMS-SRS-003","user",new UserBean());
//	}
//	@GetMapping("/displaysalary/page/{pageNo}")
//	public String displaySalaryList(@PathVariable("pageNo") int pageNo, Model model) {
//		int pageSize = 4;
//		SalaryBean bean = new SalaryBean();
//		Page<EmpSalDto> page = SalaryService.salarySearchPagi(pageNo, pageSize);
//		List<EmpSalDto> pagi = page.getContent();
//		model.addAttribute("bean", bean);
//		model.addAttribute("salarylist", pagi);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalElements", page.getTotalElements());
//		model.addAttribute("currentPage", pageNo);
//		return "EMS-PYR-003";
//
//	}

	@GetMapping("/StaffSalary/searchpage/{pageNo}")
	public String displaySerachSalary(@PathVariable("pageNo") int pageNo, String date1, String date2,
			HttpSession session, Model model) {
		int pageSize = 6;
		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
		String id = staff.getEmp_id();

		UserBean bean = new UserBean();
		bean.setId(id);
		model.addAttribute("bean", bean);
		Page<EmpSalDto> page = SalaryService.salaryStaffSearchPagi(id, date1, date2, pageNo, pageSize);
		List<EmpSalDto> list = page.getContent();

		if (id.equals("")) {
			model.addAttribute("msg", "Please Enter data to search!");
			return "redirect:/StaffSalary";
		}
		if (list.size() == 0) {
			model.addAttribute("msg", " DATA  NOT  FOUND!");
			return "EMS-SRS-003";
		} else {
			model.addAttribute("sallist", list);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalElements", page.getTotalElements());
			model.addAttribute("currentPage", pageNo);
		}
		return "EMS-SRS-003";

	}

	@GetMapping("/StaffSalary/page/{pageNo}")
	public String displaySerachSalary(@PathVariable("pageNo") int pageNo, HttpSession session, Model model) {
		int pageSize = 6;
		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
		String id = staff.getEmp_id();

		/*
		 * UserBean bean = new UserBean(); bean.setId(id); model.addAttribute("bean",
		 * bean);
		 */
		Page<EmpSalDto> page = SalaryService.salaryStaffPagi(id, pageNo, pageSize);
		List<EmpSalDto> list = page.getContent();

		if (list.size() == 0) {
			model.addAttribute("msg", " DATA  NOT  FOUND!");
			return "EMS-SRS-003";
		} else {
			model.addAttribute("sallist", list);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalElements", page.getTotalElements());
			model.addAttribute("currentPage", pageNo);
		}
		return "EMS-SRS-003";

	}

	@GetMapping("/StaffSalary")
	public String displaySalary(@ModelAttribute("bean") UserBean bean, HttpSession session, Model model) {
		String id = bean.getId();
		model.addAttribute("name", id);

		return displaySerachSalary(1, session, model);
	}
}
