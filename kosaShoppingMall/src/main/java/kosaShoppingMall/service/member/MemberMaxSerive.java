package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberMaxSerive {
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand) {
		
		String memberNum = memberMapper.memberMaxNum();
		// memberCommand.addAttribute( "maxNum" ,maxNum);
		memberCommand.setMemberNum(memberNum);
	}
}
