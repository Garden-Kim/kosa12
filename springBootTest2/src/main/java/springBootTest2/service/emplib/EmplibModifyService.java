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
public class EmplibModifyService {
	@Autowired
	EmpLibMapper empLibMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute(EmpLibCommand libCommand, Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		EmpLibDTO dto = empLibMapper.selectOne(libCommand.getLibNum());
		String empNum = dto.getEmpNum();
		EmployeeDTO dto1 = employeeMapper.selectOne(empNum);
		model.addAttribute("dto", dto);
		System.out.println("het libNum = "+dto.getLibNum());
		String path = "thymeleaf/emplib/emplibModify";
		if(!dto.getLibPw().equals(libCommand.getLibPw())) {
			model.addAttribute("err_pw","비밀번호틀림");
			path="thymeleaf/emplib/emplibInfo";
		}
		else if(!authInfo.getUserId().equals(dto1.getEmpId())) {
			model.addAttribute("err_pw","아이디틀림");
			path="thymeleaf/emplib/emplibInfo";
		}else {
			
		}
		return path;
		
	}
	
	
}
