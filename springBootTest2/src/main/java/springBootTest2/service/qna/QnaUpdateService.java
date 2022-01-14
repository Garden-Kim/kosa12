package springBootTest2.service.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.QnaCommand;
import springBootTest2.domain.QnaDTO;
import springBootTest2.mapper.QnaMapper;

@Component
@Service
public class QnaUpdateService {
	@Autowired
	QnaMapper qnaMapper;

	public void execute(QnaCommand qnaCommand) {
		QnaDTO dto = new QnaDTO();
		dto.setQnaNum(qnaCommand.getQnaNum());
		dto.setQnaContent(qnaCommand.getQnaContent());
		dto.setQnaSubject(qnaCommand.getQnaSubject());
		Integer i = qnaMapper.qnaUpdate(dto);
		
		System.out.println(i + "개 행이(가) 수정되었습니다.");
		
		
		
		
	}
	
	
}
