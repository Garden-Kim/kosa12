package springBootTest2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootTest2.command.MemberCommand;
import springBootTest2.service.membership.MemberInfoService2;
import springBootTest2.service.membership.MemberJoinService;

@Controller
@RequestMapping("membership")
public class MemberJoinController {
	@Autowired
	MemberJoinService memberJoinService; 
	@Autowired
	MemberInfoService2 memberInfoService;
	
	@RequestMapping("memberInfoModify")
	public String memberInfoModify(Model model, HttpSession session) {
		memberInfoService.execute(model, session);
		return "thymeleaf/membership/memModify";
	}
	
	@RequestMapping("memInfo")
	   public String memInfo(Model model , HttpSession session) {
	      memberInfoService.execute(model , session);
	      return "thymeleaf/membership/memInfo";
	   }

	@RequestMapping("memberAgree")
	public String memberAgree() {
		return "thymeleaf/membership/agree";
	}
	@RequestMapping("memberJoin")
	public String memberJoin() {
		return "thymeleaf/membership/memberForm";
	}
	@RequestMapping("memberWrite")
	public String memberWrite(MemberCommand memberCommand) {
		memberJoinService.execute(memberCommand);
		return "thymeleaf/membership/welcome";
	}
}
