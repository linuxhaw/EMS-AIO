package ems_aio.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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



import ems_aio.dao.BankService;
import ems_aio.dto.MBNK001;
import ems_aio.dto.MPOS001;
import ems_aio.model.BankBean;
import ems_aio.model.PositionBean;

@Controller
public class BankController {
	@Autowired
	private BankService serv;

	@GetMapping("/displaybankinfo/page/{pageNo}")
	public String bankPagi(@PathVariable(value="pageNo")int pageNo,Model model) {
		int pageSize=3;
		BankBean bean=new BankBean();
	model.addAttribute("bean", bean);
		Page<MBNK001>page=serv.bankPagi(pageNo, pageSize);
		List<MBNK001> list =page.getContent();
		model.addAttribute("banklist",list);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalElements",page.getTotalElements());
		model.addAttribute("currentPage",pageNo);
		return "EMS-MSB-003";
		
	}
	@GetMapping("/displaybankinfo")
	public String displayBank(Model model) {
		return bankPagi(1, model);
	}

	@RequestMapping(value = "/setupaddbank", method = RequestMethod.GET)
	public ModelAndView setupadduser(@ModelAttribute("bean") BankBean bean, ModelMap model) {
		MBNK001 chk = serv.findLastID();
		int Intlast=0;
		String sf2;
		BankBean bea=new BankBean();
		if (chk == null) {
			Intlast = 1;
			sf2 = String.format("BNK%03d", Intlast);
		} else {
			String StrID = chk.getBnkid();
			Intlast = Integer.parseInt(StrID.substring(3, 6))+1;
			sf2 = String.format("BNK%03d", Intlast);
		}
		bea.setId(sf2);
		model.addAttribute("bean",bea);
		return new ModelAndView("EMS-MSB-001", "bean", bea);
	}

	@RequestMapping(value = "/addbank", method = RequestMethod.POST)
	public String addposition(@ModelAttribute("bean") @Validated BankBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSB-001";
		}
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//LocalDateTime now = LocalDateTime.now();
		MBNK001 dto = new MBNK001();
		dto.setBnkid(bean.getId());
		dto.setBnkname(bean.getName());
		dto.setBnkphone(bean.getPhone());
		dto.setBnkloc(bean.getLoc());
		dto.setCreatedate(now);
		dto.setUpdatedate(now);
		dto.setStatus(b);
		Optional<MBNK001> chk = serv.getByCode(bean.getId());
		if (chk.isPresent()) {
			redirAttrs.addFlashAttribute("msg", "Timeout Session Please Tryagain");
			return "redirect:/setupaddbank";
		}
		try {
			serv.save(dto);
			redirAttrs.addFlashAttribute("msg", "Register successful");
			return "redirect:/setupaddbank";
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("err", "Register fail");
			return "EMS-MSB-001";
		}
	}
	
	@RequestMapping(value = "/setupbankupdate", method = RequestMethod.GET)
	public ModelAndView setuppositionupdate(@RequestParam("id")String id, ModelMap model) {
		Optional<MBNK001> dtoget = serv.getByCode(id);
		MBNK001 dto1=dtoget.get();
		BankBean bean = new BankBean();
		bean.setId(dto1.getBnkid());
		bean.setName(dto1.getBnkname());
		bean.setPhone(dto1.getBnkphone());
		bean.setLoc(dto1.getBnkloc());
		bean.setCreate(dto1.getCreatedate());
		return new ModelAndView("EMS-MSB-002", "bean", bean);

	}
	
	@RequestMapping(value = "/updatebank", method = RequestMethod.POST)
	public String updateposition(@ModelAttribute("bean") @Validated BankBean bean, BindingResult bs, ModelMap model,RedirectAttributes redirAttrs) {
		if (bs.hasErrors()) {
			return "EMS-MSB-002";
		}
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		boolean b = true;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//LocalDateTime now = LocalDateTime.now();
		MBNK001 dto = new MBNK001();
		dto.setBnkid(bean.getId()); 
		dto.setBnkname(bean.getName());
		dto.setBnkphone(bean.getPhone());
		dto.setBnkloc(bean.getLoc());
		dto.setCreatedate(bean.getCreate()); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		
		try {
			serv.update(dto, bean.getId());
			model.addAttribute("err", "Update successful");
			return "EMS-MSB-002";
		} catch (Exception e) {
			model.addAttribute("err", "Update fail");
			return "EMS-MSB-002";
		}
	}
	
	@RequestMapping(value = "/bankdelete", method = RequestMethod.GET)
	public String deleteposition(@RequestParam("id")String id, ModelMap model) {
		Date date=new Date();
		Timestamp now=new Timestamp(date.getTime());
		boolean b = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//LocalDateTime now = LocalDateTime.now();
		Optional<MBNK001> dtoget = serv.getByCode(id);
		MBNK001 dto=dtoget.get(); 
		dto.setUpdatedate(now);
		dto.setStatus(b);
		serv.update(dto, id);
		return "redirect:/displaybank";
	}
	
	@RequestMapping(value = "/bankSearch", method = RequestMethod.GET)
	public ModelAndView setupStudentSearch(@RequestParam(name = "message", required = false) String message,
			ModelMap model) {
		model.addAttribute("msg", message);
		return new ModelAndView("EMS-MSB-003", "bean", new BankBean());
	}

	@RequestMapping(value = "/page/searchbank", method = RequestMethod.GET)
	public String displayView(@ModelAttribute("bean") BankBean bean, ModelMap model) {
		
		List<MBNK001> list;
		String i = bean.getId();
		if (i.equals("")) {
			list = serv.getAll();
		}else {
			 list = serv.getsearch(i);
		}
		if (list.size() == 0)
			model.addAttribute("msg", "Bank not found!");
		else
			model.addAttribute("banklist", list);
		//return "BUD001";
		return "EMS-MSB-003";
	}
}
