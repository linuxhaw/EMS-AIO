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

import ems_aio.dao.PositionService;
import ems_aio.dao.StaffService;
import ems_aio.dto.MDEP001;
import ems_aio.dto.MPOS001;
import ems_aio.dto.StaffDto;
import ems_aio.model.CertifyBean;
import ems_aio.model.DepartmentBean;
import ems_aio.model.PosReportBean;
import ems_aio.model.PositionBean;
import ems_aio.model.UserBean;

@Controller
public class PositionController {
	@Autowired
	private PositionService serv;
	@Autowired
	private StaffService StaffService;

	@GetMapping("/displayposition/searchpage/{pageNo}")
	public String posPagi(@PathVariable(value="pageNo")int pageNo,
			@Param("id")String id,
			Model model) {
		
		int pageSize=3;
		PositionBean bean=new PositionBean();
		model.addAttribute("id",id);
		model.addAttribute("bean", bean);
		Page<MPOS001>page=serv.posPagi(id,pageNo, pageSize);
		List<MPOS001> list=page.getContent();
		if(id.equals("")) {
			model.addAttribute("msg","Please Enter data to search!");
			return "redirect:/displayposition";
		}
		if(list.size()==0) {
			model.addAttribute("msg", " DATA  NOT  FOUND!");
			return "EMS-MSP-003";
		}
		else {
		model.addAttribute("positionlist",list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);}
		return "EMS-MSP-003";
		
	}
	@GetMapping("/displayposition/page/{pageNo}")
	public String posPagiQuery(@PathVariable(value="pageNo")int pageNo,
			
			Model model) {
		
		int pageSize=3;
		PositionBean bean=new PositionBean();
		
		Page<MPOS001>page=serv.posPagiQuery(pageNo, pageSize);
		List<MPOS001> list=page.getContent();
		model.addAttribute("bean",bean);
		model.addAttribute("positionlist",list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);
		return "EMS-MSP-003";
		
	}
	@GetMapping("/displayposition")
	public String displayPosition(@ModelAttribute("bean")PositionBean bean,Model model) {
		String id=bean.getId();
		if(id!=null) {
		model.addAttribute("id",id);
		return posPagi(1,id, model);}
		else {
			return posPagiQuery(1,model);
		}
	}
	@RequestMapping(value = "/setupaddposition", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") PositionBean bean, ModelMap model) {
		MPOS001 chk = serv.findLastID();
		int Intlast=0;
		String sf2;
		PositionBean bea=new PositionBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("POS%03d", Intlast);
		} else {
			String StrID = chk.getPosid();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("POS%03d", Intlast);
		}
		bea.setId(sf2);
		model.addAttribute("bean",bea);
		return new ModelAndView("EMS-MSP-001", "bean", bea);
	}

	@RequestMapping(value = "/addposition", method = RequestMethod.POST)
	public String addposition(@ModelAttribute("bean") @Validated PositionBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSP-001";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MPOS001 dto = new MPOS001();

		dto.setPosid(bean.getId());
		dto.setPosname(bean.getName());
		dto.setCreatedate(now);
		dto.setUpdatedate(now);

		dto.setStatus(b);
		Optional<MPOS001> chk = serv.getPositionByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddposition";
		}
		try {
			serv.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddposition";
		} catch (Exception e) {
			model.addAttribute("err", "Register fail");
			return "EMS-MSP-001";
		}
	}
	
	@RequestMapping(value = "/setuppositionupdate", method = RequestMethod.GET)
	public ModelAndView setuppositionupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MPOS001> dtoget = serv.getPositionByCode(id);
		MPOS001 dto1=dtoget.get();
		PositionBean bean = new PositionBean();
		bean.setId(dto1.getPosid());
		bean.setName(dto1.getPosname());
		bean.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSP-002", "bean", bean);

	}
	
	@RequestMapping(value = "/updateposition", method = RequestMethod.POST)
	public String updateposition(@ModelAttribute("bean") @Validated PositionBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSP-002";
		}
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		MPOS001 dto = new MPOS001();

		dto.setPosid(bean.getId()); 
		dto.setPosname(bean.getName());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(now);

		dto.setStatus(b);
		
		try {
			serv.update(dto, bean.getId());
			model.addAttribute("msg", "Update successful");
			return "EMS-MSP-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSP-002";
		}
	}
	
	@RequestMapping(value = "/positiondelete", method = RequestMethod.GET)
	public String deleteposition(@RequestParam("id")String id, ModelMap model) {
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		Optional<MPOS001> dtoget = serv.getPositionByCode(id);
		MPOS001 dto=dtoget.get(); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		System.out.println("@@@####@@");
		serv.update(dto, id);
		return "redirect:/displayposition";
	}

	@RequestMapping(value = "/positionsearch", method = RequestMethod.GET)
	public ModelAndView setupStudentSearch(@RequestParam(name = "message", required = false) String message,
			ModelMap model) {
		model.addAttribute("msg", message);
		return new ModelAndView("EMS-MSP-003", "bean", new PositionBean());
	}

	
	@RequestMapping(value = "/setupReportPosition", method = RequestMethod.GET)
	public String setupReportPosition(Model model,HttpServletRequest request) {
		List<MPOS001> list=serv.getAll();
		PosReportBean bean=new PosReportBean();
		model.addAttribute("bean", bean);
		request.getSession().setAttribute("poslist", list);
		return  "EMS-ARP-003";
	}
	
	@GetMapping(path = "/posreportid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PosReportBean> courseName(@RequestParam(name = "id", required = true) String Id,Model model) {
		MPOS001 posobj = serv.getPositionByCode(Id).get();
		List<StaffDto> list =StaffService.getPosition(Id);
		
		PosReportBean pos=new PosReportBean();
		pos.setId(Id);
		pos.setName(posobj.getPosname());
		pos.setTotal(list.size());
		pos.setList(list);
		List<StaffDto> list1=pos.getList();
		//model.addAttribute("list", list);
		return new ResponseEntity<PosReportBean>(pos, HttpStatus.OK);
	}
}
