package springBootTest2.service.emplib;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.command.EmpLibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmpLibDTO;
import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmpLibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmpLibUpdateService {
	@Autowired
	EmpLibMapper empLibMapper;
	public void execute(EmpLibCommand empLibCommand) {
		System.out.println( "헤이헤이 "+Integer.parseInt(empLibCommand.getLibNum()));
		EmpLibDTO dto = new EmpLibDTO();
		dto.setLibContent(empLibCommand.getLibContent());
		dto.setLibPw(empLibCommand.getLibPw());
		dto.setLibSubject(empLibCommand.getLibSubject());
		dto.setLibWriter(empLibCommand.getLibWriter());
		dto.setLibNum(Integer.parseInt(empLibCommand.getLibNum()));
		
		Integer i = empLibMapper.emplibUpdate(dto);
		System.out.println(i + "개 행이 업데이트.");

		
	}
	
	
}
