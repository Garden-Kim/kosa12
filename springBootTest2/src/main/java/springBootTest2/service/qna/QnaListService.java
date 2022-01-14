package springBootTest2.service.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.QnaDTO;
import springBootTest2.mapper.QnaMapper;

@Component
@Service
public class QnaListService {
	@Autowired
	QnaMapper qnaMapper;

	public void execute(Model model) {
		List<QnaDTO> list = qnaMapper.selectAll();
		model.addAttribute("list", list);
		System.out.println(list);
	}
	
	
}
