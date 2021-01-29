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
import ems_aio.model.StaffBean;

@Controller
public class StaffController {
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
	
	@RequestMapping(value = "/displaystaff", method = RequestMethod.GET)
	public ModelAndView displayrole(Model model) {
		List<StaffDto> list;
		list = StaffService.getAll();
		StaffBean bean=new StaffBean();
		model.addAttribute("bean", bean);
		return new ModelAndView("EMS-STI-003", "stafflist", list);
		
	}
	
	@RequestMapping(value = "/setupaddstaff", method = RequestMethod.GET)
	public String setupadduser(@ModelAttribute("bean") StaffBean bean, ModelMap model,HttpServletRequest request) {
		StaffDto chk = StaffService.findLastID();
		List<MBNK001> bnkList=BankService.getAll();
		List<MROL001> rolList=RoleService.getAll();
		List<MDEP001> depList=DepartmentService.getAll();
		List<MPOS001> posList=PositionService.getAll(); 
		request.getSession().setAttribute("banklist", bnkList);
		request.getSession().setAttribute("rolelist", rolList);
		request.getSession().setAttribute("deplist", depList);
		request.getSession().setAttribute("poslist", posList);
		int Intlast=0;
		String sf2;
		StaffBean rol=new StaffBean();
		BankBean bnk=new BankBean();
		DepartmentBean dep=new DepartmentBean();
		PositionBean pos=new PositionBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("STF%04d", Intlast);
		} else {
			String StrID = chk.getId();
			Intlast = Integer.parseInt(StrID.substring(3, 7))+1;
			sf2 = String.format("STF%04d", Intlast);
		}
		rol.setId(sf2);
		model.addAttribute("bean",rol);
		model.addAttribute("bank",bnkList);
		model.addAttribute("dep",depList);
		model.addAttribute("pos", posList);
		return "EMS-STI-001";
	}
	
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
	
	@RequestMapping(value = "/addstaff", method = RequestMethod.POST)
	public String addrole(@ModelAttribute("bean") @Validated StaffBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "/addstaff";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now1 = LocalDateTime.now();
		Date date=new Date();
		System.out.println(bean.getBirthday());
		System.out.println(bean.getRegister());
		Timestamp now=new Timestamp(date.getTime());
		//System.out.println(bean.getId()+bean.getName()+bean.getPassword()+bean.getNrc()+bean.getEmail()+bean.getPhone()+bean.getSalary()+bean.getBankAcc()+bean.getBank()+bean.getAddress()+bean.getBirthday()+bean.getGender()+bean.getMarrage()+bean.getPosition()+bean.getDepartment()+bean.getRole());
		StaffDto dto = new StaffDto();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setPassword(bean.getPassword());
		dto.setNrc(bean.getNrc());
		dto.setEmail(bean.getEmail());
		dto.setPhone(bean.getPhone());
		dto.setPayroll(bean.getSalary());
		dto.setBnkacc(bean.getBankAcc());
		dto.setBank(bean.getBank());
		dto.setAddress(bean.getAddress());
		java.sql.Date sDate = convertUtilToSql(bean.getRegister());
		dto.setRegister(sDate);
		sDate= convertUtilToSql(bean.getBirthday());
		dto.setBirthday(sDate);
		dto.setGender(bean.getGender());
		dto.setMarrage(bean.getMarrage());
		dto.setReligion(bean.getReligion());
		dto.setNation(bean.getNation());
		dto.setPos(bean.getPosition());
		dto.setDep(bean.getDepartment());
		dto.setRole(bean.getRole());
		dto.setStatus(b);
		dto.setCreatedate(now);
		dto.setUpdatedate(now);
		
		Optional<StaffDto> chk = StaffService.getStaffByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddstaff";
		}
		try {
			StaffService.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddstaff";
		} catch (Exception e) {
			model.addAttribute("err", "Register fail");
			return "EMS-STI-001";
		}
	}
	
}