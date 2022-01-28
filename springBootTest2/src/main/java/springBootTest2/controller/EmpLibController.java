package springBootTest2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

import springBootTest2.command.EmpLibCommand;
import springBootTest2.service.emplib.EmpLibDeleteService;
import springBootTest2.service.emplib.EmpLibInfoService;
import springBootTest2.service.emplib.EmpLibInsertService;
import springBootTest2.service.emplib.EmpLibListService;
import springBootTest2.service.emplib.EmpLibUpdateService;
import springBootTest2.service.emplib.EmplibModifyService;
import springBootTest2.service.emplib.FileDownService;

@Controller
@RequestMapping("emplib")
public class EmpLibController {
	
	@Autowired
	EmpLibInsertService empLibInsertService;
	@Autowired
	EmpLibListService empLibListService;
	@Autowired
	EmpLibInfoService empLibInfoService;
	@Autowired
	EmpLibDeleteService empLibDeleteService;
	@Autowired
	EmpLibUpdateService empLibUpdateService;
	@Autowired
	EmplibModifyService emplibModifyService;
	@Autowired
	FileDownService fileDownService;
	@RequestMapping("fileDown")
	public void fileDown(@RequestParam(value="sfile") String sfile,
			@RequestParam("ofile") String ofile,
			HttpServletRequest request, 
			HttpServletResponse response) {
		fileDownService.execute(sfile, ofile, request,response);
	}
	
	@RequestMapping("emplibModify")
	public String emplibModify(EmpLibCommand empLibCommand, Model model,HttpSession session) {
		String path = emplibModifyService.execute(empLibCommand, model, session);
		return path;
	}


	 @RequestMapping("emplibUpdate")
	 public String emplibUpdate(EmpLibCommand empLibCommand) {
		 empLibUpdateService.execute(empLibCommand);
		 return "redirect:emplibInfo?num="+empLibCommand.getLibNum();
	 }

	@RequestMapping("emplibDetele")
	public String emplibDetele(@RequestParam("num") String num, @RequestParam("libPw") String pw, 
								Model model, HttpSession session) {
		String path = empLibDeleteService.execute(num,pw,model,session);
		return path;
	}
	
	@RequestMapping("emplibInfo")
	public String emplibInfo(@RequestParam("num") String num, Model model) {
		empLibInfoService.execute(num,model);
		return "thymeleaf/emplib/emplibInfo";
	}
	
	@RequestMapping("emplibWrite")
	public String emplibWrite(EmpLibCommand empLibCommand, HttpServletRequest request) {
		empLibInsertService.execute(empLibCommand, request);
		return "redirect:emplibList";
	}
	@RequestMapping("emplibForm")
	public String emplibForm() {
		
		return "thymeleaf/emplib/emplibForm";
		
	}
	@RequestMapping("emplibList")
	public String emplibList(Model model) {
		empLibListService.execute(model);
		return "thymeleaf/emplib/emplibList";
	}
}
