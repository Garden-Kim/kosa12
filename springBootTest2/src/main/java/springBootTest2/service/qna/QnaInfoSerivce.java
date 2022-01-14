package springBootTest2.service.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.command.QnaCommand;
import springBootTest2.domain.QnaDTO;
import springBootTest2.mapper.QnaMapper;

@Component
@Service
public class QnaInfoSerivce {
	@Autowired
	QnaMapper qnaMapper;
	public void execute(Integer num, Model model) {
		qnaMapper.visitCount(num);
		QnaDTO dto = qnaMapper.selectOne(num);
		model.addAttribute("dto",dto);
		
	}
	
}
