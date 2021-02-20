package ems_aio.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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

import ems_aio.dao.CertifyService;
import ems_aio.dto.MBNK001;
import ems_aio.dto.MCTF001;
import ems_aio.dto.MDEP001;
import ems_aio.model.BankBean;
import ems_aio.model.CertifyBean;
import ems_aio.model.DepartmentBean;

@Controller
public class CertifyController {
	@Autowired
	private CertifyService serv;

//	@RequestMapping(value = "/displaycertify", method = RequestMethod.GET)
//	public ModelAndView displayQualification(Model model) {
//		List<MCTF001> list;
//		list = serv.getAll();
//		CertifyBean bean=new CertifyBean();
//		model.addAttribute("bean", bean);
//		return new ModelAndView("EMS-MSC-003", "certifylist", list);
//	}
//	
	@GetMapping("/displaycertify/searchpage/{pageNo}")
	public String certifyPagi(@PathVariable(value="pageNo")int pageNo,
			@Param("id")String id,
			Model model) {
		
		int pageSize=6;
		CertifyBean bean=new CertifyBean();
		model.addAttribute("id",id);
		model.addAttribute("bean", bean);
		Page<MCTF001>page=serv.certifyPagi(id,pageNo, pageSize);
		List<MCTF001> list=page.getContent();
		if(id.equals("")) {
			model.addAttribute("msg","Please Enter data to search!");
			return "redirect:/displaycertify";
		}
		if(list.size()==0) {
			model.addAttribute("msg", " DATA  NOT  FOUND!");
			return "EMS-MSC-003";
		}
		else {
		model.addAttribute("certifylist",list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);}
		return "EMS-MSC-003";
		
	}
	@GetMapping("/displaycertify/page/{pageNo}")
	public String certifyPagiQuery(@PathVariable(value="pageNo")int pageNo,
			
			Model model) {
		
		int pageSize=6;
		CertifyBean bean=new CertifyBean();
		
		Page<MCTF001>page=serv.certifyPagiQuery(pageNo, pageSize);
		List<MCTF001> list=page.getContent();
		model.addAttribute("bean",bean);
		model.addAttribute("certifylist",list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);
		return "EMS-MSC-003";
		
	}
	@GetMapping("/displaycertify")
	public String displaycertify(@ModelAttribute("bean")CertifyBean bean,Model model) {
		String id=bean.getId();
		if(id!=null) {
		model.addAttribute("id",id);
		return certifyPagi(1,id, model);}
		else {
			return certifyPagiQuery(1,model);
		}
	}
	@RequestMapping(value = "/setupaddcertify", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") CertifyBean bean, ModelMap model) {
		MCTF001 chk = serv.findLastID();
		int Intlast=0;
		String sf2;
		CertifyBean bea=new CertifyBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("CTF%03d", Intlast);
		} else {
			String StrID = chk.getId();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("CTF%03d", Intlast);
		}
		bea.setId(sf2);
		model.addAttribute("bean",bea);
		return new ModelAndView("EMS-MSC-001", "bean", bea);
	}

	@RequestMapping(value = "/addcertify", method = RequestMethod.POST)
	public String addposition(@ModelAttribute("bean") @Validated CertifyBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSC-001";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MCTF001 dto = new MCTF001();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setSchool(bean.getSchool());
		dto.setCreatedate(now);
		dto.setUpdatedate(now);
		dto.setStatus(b);
		Optional<MCTF001> chk = serv.getByCode(bean.getId());
		System.out.println("Hell");
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddcertify";
		}
		try {
			serv.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddcertify";
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("err", "Register fail");
			return "EMS-MSC-001";
		}
	}
	
	@RequestMapping(value = "/setupcertifyupdate", method = RequestMethod.GET)
	public ModelAndView setuppositionupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MCTF001> dtoget = serv.getByCode(id);
		MCTF001 dto1=dtoget.get();
		CertifyBean bean = new CertifyBean();
		bean.setId(dto1.getId());
		bean.setName(dto1.getName());
		bean.setSchool(dto1.getSchool());
		bean.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSC-002", "bean", bean);

	}
	
	@RequestMapping(value = "/updatecertify", method = RequestMethod.POST)
	public String updateposition(@ModelAttribute("bean") @Validated CertifyBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSC-002";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MCTF001 dto = new MCTF001();
		dto.setId(bean.getId()); 
		dto.setName(bean.getName());
		dto.setSchool(bean.getSchool());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		try {
			serv.update(dto, bean.getId());
			model.addAttribute("msg", "Update successful");
			return "EMS-MSC-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSC-002";
		}
	}
	
	@RequestMapping(value = "/certifydelete", method = RequestMethod.GET)
	public String deleteposition(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Optional<MCTF001> dtoget = serv.getByCode(id);
		MCTF001 dto=dtoget.get(); 
		//dto.setUpdatedate(dtf.format(now));
		dto.setStatus(b);
		serv.update(dto, id);
		return "redirect:/displaycertify";
	}
//	@RequestMapping(value = "/certifysearch", method = RequestMethod.GET)
//	public ModelAndView setupStudentSearch(@RequestParam(name = "message", required = false) String message,
//			ModelMap model) {
//		model.addAttribute("msg", message);
//		return new ModelAndView("EMS-MSC-003", "bean", new CertifyBean());
//	}
//	@RequestMapping(value = "/page/searchcertification", method = RequestMethod.GET)
//	public String displayView(@ModelAttribute("bean") CertifyBean bean, ModelMap model) {
//		
//		List<MCTF001> list;
//		String i = bean.getId();
//		if (i.equals("")) {
//			list = serv.getAll();
//		}else {
//			 list = serv.getsearch(i);
//		}
//		if (list.size() == 0)
//			model.addAttribute("msg", "Certification not found!");
//		else
//			model.addAttribute("certifylist", list);
//		//return "BUD001";
//		return "EMS-MSC-003";
//	}
}
