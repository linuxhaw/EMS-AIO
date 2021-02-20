package ems_aio.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import ems_aio.model.CertifyBean;
import ems_aio.model.DateBean;
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
		model.addAttribute("sallist",sallist);
		return new ModelAndView("EMS-SRS-003","date",new DateBean());
	}
	
	@RequestMapping(value="/salarysearch" ,method=RequestMethod.GET)
	public ModelAndView salarysearch(HttpSession session,Model model,@ModelAttribute("date")DateBean date) {
		StaffDto staff = (StaffDto) session.getAttribute("sesUser");
		String id =staff.getEmp_id();
		String date1;
		System.out.println("hello");
		if (date.getSaldate1().equals(null)) {
			date1 = "1990-01-01";
		}else {
			date1 = date.getSaldate1();
		}
		System.out.println(date1);
		String date2;
		if (date.getSaldate2().equals(null)) {
			Date today = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
			date2 = dateFormat.format(today); 
		}else {
			date2 = date.getSaldate2();
		}
		System.out.println(date2);
		List<EmpSalDto> sallist=SalaryService.getStaffSalSearch(id,date1,date2); 
		model.addAttribute("sallist",sallist);
		return new ModelAndView("EMS-SRS-003","date",new DateBean());
	}
}
