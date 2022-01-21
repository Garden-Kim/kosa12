package springBootTest2.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.domain.MemberDTO;
import springBootTest2.mapper.MemberMapper;

@Component
@Service
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String num) {
		Integer i = (Integer) memberMapper.memberDelete(num);
		
		System.out.println(i + "개 행이 삭제되었습니다.");
		
	}

}
