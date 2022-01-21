package springBootTest2.service.emplib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.EmpLibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmpLibDTO;
import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmpLibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmpLibInsertService {
	@Autowired
	EmpLibMapper empLibMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmpLibCommand empLibCommand, HttpServletRequest request) {
		EmpLibDTO dto = new EmpLibDTO();
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		dto.setEmpNum(employeeMapper.empNumSelect(authInfo.getUserId()));
		dto.setIpAddr(request.getRemoteAddr());
		dto.setLibContent(empLibCommand.getLibContent());
		dto.setLibPw(empLibCommand.getLibPw());
		dto.setLibSubject(empLibCommand.getLibSubject());
		dto.setLibWriter(empLibCommand.getLibWriter());
		
		Integer i = empLibMapper.empLibInsert(dto);
		System.out.println(i + "개 행이 삽입되었습니다.");
	}

}
