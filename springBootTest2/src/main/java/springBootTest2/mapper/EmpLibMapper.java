package springBootTest2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.EmpLibDTO;

@Component
@Repository("springBootTest2.mapper.EmpLibMapper")
public interface EmpLibMapper {

	Integer empLibInsert(EmpLibDTO dto);

	List<EmpLibDTO> selectAll();

	EmpLibDTO selectOne(String num);

	Integer emplibDelte(String num);

	Integer emplibUpdate(EmpLibDTO dto);
	
}
