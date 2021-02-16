package ems_aio.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import ems_aio.dao.StaffService;
import ems_aio.dto.MCTF001;
import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.StaffDto;
import ems_aio.model.BankBean;
import ems_aio.model.CertifyBean;
import ems_aio.model.DepReportBean;
import ems_aio.model.DepartmentBean;
import ems_aio.model.PosReportBean;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService serv;
	@Autowired
	private StaffService StaffService;

//	@RequestMapping(value = "/displaydepartment", method = RequestMethod.GET)
//	public ModelAndView displayQualification(Model model) {
//		List<MDEP001> list;
//		list = serv.getAll();
//		DepartmentBean bean=new DepartmentBean();
//		model.addAttribute("bean", bean);
//		return new ModelAndView("EMS-MSD-003", "departmentlist", list);
//	}
//	
	@GetMapping("/displaydepartment/searchpage/{pageNo}")
	public String depPagi(@PathVariable(value="pageNo")int pageNo,
			@Param("id")String id,
			Model model) {
		
		int pageSize=3;
		DepartmentBean bean=new DepartmentBean();
		model.addAttribute("id",id);
		model.addAttribute("bean", bean);
		Page<MDEP001>page=serv.depPagi(id,pageNo, pageSize);
		List<MDEP001> list=page.getContent();
		if(id.equals("")) {
			model.addAttribute("msg","Please Enter data to search!");
			return "redirect:/displaydepartment";
		}
		if(list.size()==0) {
			model.addAttribute("msg", " DATA  NOT  FOUND!");
			return "EMS-MSD-003";
		}
		else {
		model.addAttribute("departmentlist",list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);}
		return "EMS-MSD-003";
		
	}
	@GetMapping("/displaydepartment/page/{pageNo}")
	public String certifyPagiQuery(@PathVariable(value="pageNo")int pageNo,
			
			Model model) {
		
		int pageSize=3;
		DepartmentBean bean=new DepartmentBean();
		
		Page<MDEP001>page=serv.depPagiQuery(pageNo, pageSize);
		List<MDEP001> list=page.getContent();
		model.addAttribute("bean",bean);
		model.addAttribute("departmentlist",list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);
		return "EMS-MSD-003";
		
	}
	@GetMapping("/displaydepartment")
	public String displaydepartment(@ModelAttribute("bean")CertifyBean bean,Model model) {
		String id=bean.getId();
		if(id!=null) {
		model.addAttribute("id",id);
		return depPagi(1,id, model);}
		else {
			return certifyPagiQuery(1,model);
		}
	}
	@RequestMapping(value = "/setupadddepartment", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") DepartmentBean bean, ModelMap model) {
		MDEP001 chk = serv.findLastID();
		int Intlast=0;
		String sf2;
		DepartmentBean bea=new DepartmentBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("DEP%03d", Intlast);
		} else {
			String StrID = chk.getId();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("DEP%03d", Intlast);
		}
		bea.setId(sf2);
		model.addAttribute("bean",bea);
		return new ModelAndView("EMS-MSD-001", "bean", bea);
	}

	@RequestMapping(value = "/adddepartment", method = RequestMethod.POST)
	public String addposition(@ModelAttribute("bean") @Validated DepartmentBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSD-001";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MDEP001 dto = new MDEP001();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setLoc(bean.getLoc());
		dto.setHead(bean.getHead());
		dto.setCreatedate(now);
		dto.setUpdatedate(now);
		dto.setStatus(b);
		Optional<MDEP001> chk = serv.getByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupadddepartment";
		}
		try {
			serv.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupadddepartment";
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("err", "Register fail");
			return "EMS-MSD-001";
		}
	}
	
	@RequestMapping(value = "/setupdepartmentupdate", method = RequestMethod.GET)
	public ModelAndView setuppositionupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MDEP001> dtoget = serv.getByCode(id);
		MDEP001 dto1=dtoget.get();
		DepartmentBean bean = new DepartmentBean();
		bean.setId(dto1.getId());
		bean.setName(dto1.getName());
		bean.setLoc(dto1.getLoc());
		bean.setHead(dto1.getHead());
		bean.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSD-002", "bean", bean);

	}
	
	@RequestMapping(value = "/updatedepartment", method = RequestMethod.POST)
	public String updateposition(@ModelAttribute("bean") @Validated DepartmentBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSD-002";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MDEP001 dto = new MDEP001();
		dto.setId(bean.getId()); 
		dto.setName(bean.getName());
		dto.setLoc(bean.getLoc());
		dto.setHead(bean.getHead());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		try {
			serv.update(dto, bean.getId());
			model.addAttribute("msg", "Update successful");
			return "EMS-MSD-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSD-002";
		}
	}
	
	@RequestMapping(value = "/departmentdelete", method = RequestMethod.GET)
	public String deleteposition(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		Optional<MDEP001> dtoget = serv.getByCode(id);
		MDEP001 dto=dtoget.get(); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		serv.update(dto, id);
		return "redirect:/displaydepartment";
	}
	
	@RequestMapping(value = "/setupReportDepartment", method = RequestMethod.GET)
	public String setupReportPosition(Model model,HttpServletRequest request) {
		List<MDEP001> list=serv.getAll();
		DepReportBean bean=new DepReportBean();
		model.addAttribute("bean", bean);
		request.getSession().setAttribute("deplist", list);
		return  "EMS-ARD-003";
	}
	
	@GetMapping(path = "/depreportid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepReportBean> courseName(@RequestParam(name = "id", required = true) String Id,Model model) {
		MDEP001 obj = serv.getByCode(Id).get();
		List<StaffDto> list =StaffService.getDepartment(Id);
		DepReportBean dep=new DepReportBean();
		dep.setId(Id);
		dep.setName(obj.getName());
		dep.setTotal(list.size());
		dep.setHead(obj.getHead());
		dep.setLocation(obj.getLoc());
		dep.setList(list);
		return new ResponseEntity<DepReportBean>(dep, HttpStatus.OK);
	}
	
	
}