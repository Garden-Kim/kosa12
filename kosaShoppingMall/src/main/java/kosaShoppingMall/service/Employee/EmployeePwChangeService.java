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
public class EmployeePwChangeService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(EmployeeCommand employeeCommand, Model model) {
		String path = "thymeleaf/employee/empPwChange";
		EmployeeDTO dto = employeeMapper.employeeSelectOne(employeeCommand.getEmpId());
		if(passwordEncoder.matches(employeeCommand.getEmpPw(), dto.getEmpPw()));
		else {
			model.addAttribute("employeeCommand" , dto);
			model.addAttribute("err_pw" , "비밀번호가 틀렸네요");
			path ="thymeleaf/emp/empDetail";
		}
		return path;
	}

}
