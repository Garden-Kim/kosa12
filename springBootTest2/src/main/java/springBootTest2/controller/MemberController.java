package springBootTest2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.MemberCommand;
import springBootTest2.service.member.MemberAutoNumService;
import springBootTest2.service.member.MemberDeleteService;
import springBootTest2.service.member.MemberInfoService;
import springBootTest2.service.member.MemberListService;
import springBootTest2.service.member.MemberUpdateService;
import springBootTest2.service.member.MemberWriteService;

@Controller
@RequestMapping("mem")
public class MemberController {
	@Autowired
	MemberListService memberListService; 
	@Autowired
	MemberAutoNumService memberAutoNumService;
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberDeleteService memberDeleteService;
	@Autowired
	MemberUpdateService memberUpdateService;
	
	@RequestMapping("memberUpdateOk")
	public String memberUpdateOk(MemberCommand memberCommand) {
		memberUpdateService.execute(memberCommand);
		return "redirect:memberInfo?num=" + memberCommand.getMemNum();
	}
	
	@RequestMapping("memberUpdate")
	public String memberUpdate(@RequestParam("num") String num, Model model) {
		memberInfoService.execute(num, model);
		return "thymeleaf/member/memberUpdate";
	}
	
	@RequestMapping("memberDelete")
	public String memberDelete(@RequestParam("num") String num) {
		memberDeleteService.execute(num);
		return "redirect:memberList";
	}
	
	@RequestMapping("memberInfo")
	public String memberInfo(@RequestParam("num") String num, Model model) {
		memberInfoService.execute(num, model);
		return "thymeleaf/member/memberInfo";
	}
	
	@RequestMapping("memberList")
	public String memberList(Model model) {
		memberListService.execute(model);
		return "thymeleaf/member/memberList";
	}
	@RequestMapping("memberRegist")
	public String memberRegist(Model model) {
		memberAutoNumService.execute(model);
		return "thymeleaf/member/memberForm";
	}
	@RequestMapping("memberWrite")
	public String memberWrite(MemberCommand memberCommand) {
		memberWriteService.execute(memberCommand);
		return "redirect:memberList";
	}
}
