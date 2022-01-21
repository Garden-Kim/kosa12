package springBootTest2.service.emplib;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmpLibDTO;
import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmpLibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmpLibDeleteService {
	@Autowired
	EmpLibMapper empLibMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute(String num, String pw, Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		EmpLibDTO dto = empLibMapper.selectOne(num);
		String empNum = dto.getEmpNum();
		EmployeeDTO dto1 = employeeMapper.selectOne(empNum);
		model.addAttribute("dto", dto);
		System.out.println("het libNum = "+dto.getLibNum());
		String path = "redirect:emplibList";
		if(!dto.getLibPw().equals(pw)) {
			model.addAttribute("err_pw","비밀번호틀림");
			path="thymeleaf/emplib/emplibInfo";
		}
		else if(!authInfo.getUserId().equals(dto1.getEmpId())) {
			model.addAttribute("err_pw","아이디틀림");
			path="thymeleaf/emplib/emplibInfo";
		}else {
			Integer i = empLibMapper.emplibDelte(num);
		}
		return path;
	}
	
}
