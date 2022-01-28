package kosaShoppingMall.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MeberListService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(Model model) {
		List<MemberDTO> list = memberMapper.memberSelectAll();
		model.addAttribute("list" , list);
	}
}
