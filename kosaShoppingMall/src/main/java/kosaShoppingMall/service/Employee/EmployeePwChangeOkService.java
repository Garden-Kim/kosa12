package kosaShoppingMall.service.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeePwChangeOkService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(EmployeeCommand employeeCommand, Model model) {
		String path = "redirect:empList";
		EmployeeDTO dto = employeeMapper.employeeSelectOne(employeeCommand.getEmpId());

		if(employeeCommand.getEmpPw().equals(employeeCommand.getEmpPwCon())) {
			String empPw = passwordEncoder.encode(employeeCommand.getEmpPw());
			dto.setEmpPw(empPw);
			dto.getEmpId();
			Integer i = employeeMapper.employeePwChageOk(dto);
			
		}else {
			model.addAttribute("employeeCommand" , dto);
			model.addAttribute("err_pw" , "비밀번호가 다릅니다.");
			path = "thymeleaf/emp/empPwChange";
		}
		return path;
	}
	
}
