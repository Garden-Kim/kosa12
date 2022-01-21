package springBootTest2.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.mapper.GoodsMapper;

@Component
@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;

	public void execute(String num) {
		Integer i = goodsMapper.goodsDelete(num);
		System.out.println(i + "개 행이 삭제.");
		
	}
	
}
