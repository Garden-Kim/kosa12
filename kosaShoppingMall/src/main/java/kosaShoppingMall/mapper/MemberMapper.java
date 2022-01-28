package kosaShoppingMall.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.MemberDTO;

@Component
@Repository("kosaShoppingMall.mapper.MemberMapper")
public interface MemberMapper {
	public String memberMaxNum();
	public Integer memberInsert(MemberDTO dto);
	public List<MemberDTO> memberSelectAll();
	public MemberDTO memberSelectOne(String memberNum);
	public Integer memberUpdate(MemberDTO dto);
	public Integer memberDelete(String memberNum);
}
