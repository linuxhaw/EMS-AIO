package ems_aio.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ems_aio.dao.MovementService;
import ems_aio.dao.SalaryService;
import ems_aio.dao.StaffService;
import ems_aio.dto.EmpMovDto;
import ems_aio.dto.EmpSalDto;
import ems_aio.dto.MPOS001;
import ems_aio.dto.StaffDto;
import ems_aio.model.UserBean;
@Controller
public class StaffViewController {
	@Autowired
	private StaffService StaffService;
	@Autowired
	private MovementService MovementService;
	@Autowired
	private SalaryService SalaryService;
	
	@RequestMapping(value="/StaffProfile" ,method=RequestMethod.GET)
	public ModelAndView StaffProfile() {
		
		return new ModelAndView("EMS-SRP-003","user",new UserBean());
	}
	
	@RequestMapping(value="/StaffMovement" ,method=RequestMethod.GET)
	public ModelAndView StaffMovement(HttpSession session,Model model) {
		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
		String id =staff.getEmp_id();
		List<EmpMovDto> movlist=MovementService.getStaffMov(id); 
		model.addAttribute("movlist",movlist);
		return new ModelAndView("EMS-SRM-003","user",new UserBean());
	}
	
	@RequestMapping(value="/StaffSalary" ,method=RequestMethod.GET)
	public ModelAndView StaffSalary(HttpSession session,Model model) {
		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
		String id =staff.getEmp_id();
		List<EmpSalDto> sallist=SalaryService.getStaffSal(id); 
		System.out.println(sallist.size());
		model.addAttribute("sallist",sallist);
		return new ModelAndView("EMS-SRS-003","user",new UserBean());
	}
}
