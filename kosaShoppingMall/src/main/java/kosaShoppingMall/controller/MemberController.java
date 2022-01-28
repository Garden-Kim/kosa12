package kosaShoppingMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.service.member.MeberDetailService;
import kosaShoppingMall.service.member.MeberListService;
import kosaShoppingMall.service.member.MeberWriteService;
import kosaShoppingMall.service.member.MemberDeleteService;
import kosaShoppingMall.service.member.MemberMaxSerive;
import kosaShoppingMall.service.member.MemberUpdateService;

@Component
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberMaxSerive memberMaxSerive;
	@Autowired
	MeberWriteService meberWriteService;
	@Autowired
	MeberListService meberListService;
	@Autowired
	MeberDetailService meberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@ModelAttribute
	MemberCommand setMemberCommand() {
		return new MemberCommand();
	}
	
	@RequestMapping("memberDelete")
	public String memberDelete(@RequestParam(value="num") String memberNum ,Model model) {
		memberDeleteService.execute(memberNum , model);
		return "thymeleaf/member/memberdel";
		 // return "redirect:memberList";
	}
	
	@RequestMapping(value="memberModify" ,method = RequestMethod.POST)
	public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memberUpdate";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memberDetail/"+ memberCommand.getMemberNum();
	}
	
	@RequestMapping(value="memberModify" ,method = RequestMethod.GET)
	public String memberModify(@RequestParam(value="memberNum")String memberNum , Model model) {
		meberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberUpdate";
	}
	
								//id는 내가 넘긴값도 주소입니다 하고 id로 받겠다라고 한것이다. 주소라서 /를 붙여야한다.
	@RequestMapping("memberDetail/{num}")
	public String memberDetail(@PathVariable(value="num")String memberNum , Model model) {
		meberDetailService.execute(memberNum , model);
		return "thymeleaf/member/memberDetail";
	}
	
	
	
	@RequestMapping( value="memberRegist", method =RequestMethod.POST )
	public String memberRegist(@Validated MemberCommand memberCommand, BindingResult result){
		memberMaxSerive.execute(memberCommand);
		if(result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		if(!memberCommand.ismemberPwismemberPwCon()){
			result.rejectValue("memberPw", "memberCommand.memberPw", "비밀번호 확인이 다릅니다.");
		//	model.addAttribute("err_pw" , "보노보노");
			return "thymeleaf/member/memberForm";
		}meberWriteService.execute(memberCommand);
		return "redirect:memberList";
		
	}
	
	@RequestMapping(value="memberRegist", method =RequestMethod.GET )
	public String memberForm(MemberCommand memberCommand){
		memberMaxSerive.execute(memberCommand);
		return "thymeleaf/member/memberForm";
	}
	
	@RequestMapping("memberList")
	public String memList(Model model) {
		meberListService.execute(model);
		return "thymeleaf/member/memberList";	
	}
	

}
