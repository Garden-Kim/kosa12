package springBootTest2.service.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.domain.QnaDTO;
import springBootTest2.mapper.QnaMapper;

@Component
@Service
public class QnaDeleteService {
	@Autowired
	QnaMapper qnaMapper;

	public void execute(Integer num) {
		Integer i = qnaMapper.qnaDelete(num);
		
		System.out.println(i + "개 행이(가) 삭제되었습니다.");
		
		
		
		
	}

}
