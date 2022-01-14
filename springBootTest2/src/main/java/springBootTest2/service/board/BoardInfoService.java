package springBootTest2.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.BoardDTO;
import springBootTest2.mapper.BoardMapper;

@Component
@Service
public class BoardInfoService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(Integer num, Model model) {
		BoardDTO dto = boardMapper.selectOne(num);
		model.addAttribute("dto", dto);
	}

}
