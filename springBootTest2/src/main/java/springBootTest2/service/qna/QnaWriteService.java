package springBootTest2.service.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.BoardCommand;
import springBootTest2.command.QnaCommand;
import springBootTest2.domain.QnaDTO;
import springBootTest2.mapper.QnaMapper;

@Component
@Service
public class QnaWriteService {
	@Autowired
	QnaMapper qnaMapper;
	public void execute(QnaCommand qnaCommand) {
		QnaDTO dto = new QnaDTO();
		dto.setQnaContent(qnaCommand.getQnaContent());
		dto.setQnaSubject(qnaCommand.getQnaSubject());

		Integer i = qnaMapper.qnaInsert(dto);
		

		System.out.println(i + "개 행이(가) 삽입되었습니다.");
		
	}
	
}
