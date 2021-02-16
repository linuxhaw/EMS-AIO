package ems_aio.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import ems_aio.dao.CertifyService;
import ems_aio.dao.QualifyService;
import ems_aio.dto.MBNK001;
import ems_aio.dto.MCTF001;
import ems_aio.dto.MQUL001;
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
	@Autowired
	private CertifyService CertifyService;
	@Autowired
	private QualifyService QualifyService;
	
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
		List<MCTF001> ctfList=CertifyService.getAll(); 
		List<MQUL001> qulList=QualifyService.getAll(); 
		request.getSession().setAttribute("banklist", bnkList);
		request.getSession().setAttribute("rolelist", rolList);
		request.getSession().setAttribute("deplist", depList);
		request.getSession().setAttribute("poslist", posList);
		request.getSession().setAttribute("ctflist", ctfList);
		request.getSession().setAttribute("qullist", qulList);
		int Intlast=0;
		String sf2;
		StaffBean staff=new StaffBean();
		BankBean bnk=new BankBean();
		DepartmentBean dep=new DepartmentBean();
		PositionBean pos=new PositionBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("STF%04d", Intlast);
		} else {
			String StrID = chk.getEmp_id();
			Intlast = Integer.parseInt(StrID.substring(3, 7))+1;
			sf2 = String.format("STF%04d", Intlast);
		}
		staff.setId(sf2);
		staff.setGender("Male");
		staff.setMarrage("Single");
		staff.setNation("Citizen");
		staff.setReligion("Christians");
		model.addAttribute("bean",staff);
		return "EMS-STI-001";
	}

	@RequestMapping(value = "/addstaff", method = RequestMethod.POST)
	public String addrole(@ModelAttribute("bean") @Validated StaffBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs,@RequestParam(value = "cers" , required = false) String[] cers,@RequestParam(value = "quls" , required = false) String[] quls) {
		if (bs.hasErrors()) {
			return "EMS-STI-001";
		}
		boolean b = true;
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		StaffDto dto = new StaffDto();
		dto.setEmp_id(bean.getId());
		dto.setEmp_name(bean.getName());
		dto.setEmp_password(bean.getPassword());
		dto.setEmp_nrc(bean.getNrc());
		dto.setEmp_email(bean.getEmail());
		dto.setEmp_phone(bean.getPhone());
		dto.setEmp_payroll(bean.getSalary());
		dto.setEmp_bnkacc(bean.getBankAcc());
		dto.setEmp_bnk(bean.getBank());
		dto.setEmp_address(bean.getAddress());
		dto.setEmp_register(java.sql.Date.valueOf(bean.getRegister()));
		dto.setEmp_birthday(java.sql.Date.valueOf(bean.getBirthday()));
		dto.setEmp_gender(bean.getGender());
		dto.setEmp_marrage(bean.getMarrage());
		dto.setEmp_religion(bean.getReligion());
		dto.setEmp_nationality(bean.getNation());
		dto.setEmp_pos(bean.getPosition());
		dto.setEmp_dep(bean.getDepartment());
		dto.setEmp_rol(bean.getRole());
		dto.setEmp_status(b);
		dto.setEmp_create(now);
		dto.setEmp_update(now);
		
		if(cers != null) {
			Set<MCTF001> certificate = new HashSet<MCTF001>();
			for (int i = 0; i < cers.length; i++) {
				System.out.println(cers[i]);
				Optional<MCTF001> addi=CertifyService.getByCode(cers[i]);
				if (addi != null) {
					MCTF001 i1=addi.get();
					certificate.add(i1);
				}
				
			}
			dto.setCtf(certificate);
		}
		
		if(quls != null) {
			Set<MQUL001> qualification = new HashSet<MQUL001>();
			for (int i = 0; i < quls.length; i++) {
				Optional<MQUL001> addi=QualifyService.getByCode(quls[i]);
				if (addi != null) {
					MQUL001 i1=addi.get();
					qualification.add(i1);
				}
				
			}
			dto.setQul(qualification);
		}
		
		Optional<StaffDto> chk = StaffService.getByCode(bean.getId());
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
	
	@RequestMapping(value = "/setupstaffupdate", method = RequestMethod.GET)
	public ModelAndView setuproleupdate(@RequestParam("id")String id, ModelMap model,HttpServletRequest request) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<MBNK001> bnkList=BankService.getAll();
		List<MROL001> rolList=RoleService.getAll();
		List<MDEP001> depList=DepartmentService.getAll();
		List<MPOS001> posList=PositionService.getAll(); 
		List<MCTF001> ctfList=CertifyService.getAll(); 
		List<MQUL001> qulList=QualifyService.getAll(); 
		request.getSession().setAttribute("banklist", bnkList);
		request.getSession().setAttribute("rolelist", rolList);
		request.getSession().setAttribute("deplist", depList);
		request.getSession().setAttribute("poslist", posList);
		request.getSession().setAttribute("ctflist", ctfList);
		request.getSession().setAttribute("qullist", qulList);
		Optional<StaffDto> dtoget = StaffService.getByCode(id);
		StaffDto dto1=dtoget.get();
		StaffBean staff = new StaffBean();
		staff.setId(dto1.getEmp_id());
		staff.setName(dto1.getEmp_name());
		staff.setPassword(dto1.getEmp_password());
		staff.setNrc(dto1.getEmp_nrc());
		staff.setEmail(dto1.getEmp_email());
		staff.setPhone(dto1.getEmp_phone());
		staff.setSalary(dto1.getEmp_payroll());
		staff.setBankAcc(dto1.getEmp_bnkacc());
		staff.setBank(dto1.getEmp_bnk());
		staff.setAddress(dto1.getEmp_address());
		staff.setRegister(dateFormat.format(dto1.getEmp_register()));
		staff.setBirthday(dateFormat.format(dto1.getEmp_birthday()));
		staff.setGender(dto1.getEmp_gender());
		staff.setMarrage(dto1.getEmp_marrage());
		staff.setReligion(dto1.getEmp_religion());
		staff.setNation(dto1.getEmp_nationality());
		staff.setDepartment(dto1.getEmp_dep());
		staff.setRole(dto1.getEmp_rol());
		staff.setPosition(dto1.getEmp_pos());
		staff.setCertify(dto1.getCtf());
		List<String> qul=new ArrayList<String>(); 
		
		for (MQUL001 qu : dto1.getQul()) {
			qul.add(qu.getQulid());
		}
		System.out.println(qul.contains("CTF002"));
		request.getSession().setAttribute("qulselect", qul);
		return new ModelAndView("EMS-STI-002", "bean", staff);
	}
	
	@RequestMapping(value = "/updatestaff", method = RequestMethod.POST)
	public String updaterole(@ModelAttribute("bean") @Validated StaffBean bean, BindingResult bs, ModelMap model) {
		if (bs.hasErrors()) {
			return "EMS-MSR-002";
		}
		boolean b = true;
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		StaffDto dto = new StaffDto();
		dto.setEmp_id(bean.getId());
		dto.setEmp_name(bean.getName());
		dto.setEmp_password(bean.getPassword());
		dto.setEmp_nrc(bean.getNrc());
		dto.setEmp_email(bean.getEmail());
		dto.setEmp_phone(bean.getPhone());
		dto.setEmp_payroll(bean.getSalary());
		dto.setEmp_bnkacc(bean.getBankAcc());
		dto.setEmp_bnk(bean.getBank());
		dto.setEmp_address(bean.getAddress());
		dto.setEmp_register(java.sql.Date.valueOf(bean.getRegister()));
		dto.setEmp_birthday(java.sql.Date.valueOf(bean.getBirthday()));
		dto.setEmp_gender(bean.getGender());
		dto.setEmp_marrage(bean.getMarrage());
		dto.setEmp_religion(bean.getReligion());
		dto.setEmp_nationality(bean.getNation());
		dto.setEmp_pos(bean.getPosition());
		dto.setEmp_dep(bean.getDepartment());
		dto.setEmp_rol(bean.getRole());
		dto.setEmp_status(b);
		dto.setEmp_update(now);
		try {
			StaffService.update(dto, bean.getId());
			model.addAttribute("msg", "Update successful");
			return "EMS-STI-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-STI-002";
		}
	}
	/*
	@RequestMapping(value = "/roledelete", method = RequestMethod.GET)
	public String deleterole(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Optional<MROL001> dtoget = RoleService.getRoleByCode(id);
		MROL001 dto=dtoget.get(); 
		dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		RoleService.update(dto, id);
		return "redirect:/displayrole";
	}
	@RequestMapping(value = "/searchrole", method = RequestMethod.GET)
	public String displayView(@ModelAttribute("bean") RoleBean bean, ModelMap model) {
		
		List<MROL001> list;
		String i = bean.getId();
		if (i.equals("")) {
			list = RoleService.getAll();
		}else {
			 list = RoleService.getsearchrole(i);
		}
		System.out.println(list.size());
		if (list.size() == 0)
			model.addAttribute("msg", "User not found!");
		else
			model.addAttribute("rolelist", list);
		//return "BUD001";
		return "EMS-MSR-003";
	}
	*/
}