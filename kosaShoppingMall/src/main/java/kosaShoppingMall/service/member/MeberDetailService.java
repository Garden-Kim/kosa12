package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MeberDetailService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memberNum, Model model) {
		MemberDTO dto = memberMapper.memberSelectOne(memberNum);
		model.addAttribute("memberCommand" , dto);
	}
}
