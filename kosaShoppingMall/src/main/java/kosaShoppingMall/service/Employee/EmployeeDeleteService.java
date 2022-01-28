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
public class EmployeeDeleteService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(String empId, String empPw, EmployeeCommand employeeCommand, Model model) {
		EmployeeDTO dto = employeeMapper.employeeSelectOne(employeeCommand.getEmpId());
	
		if(passwordEncoder.matches(employeeCommand.getEmpPw(),dto.getEmpPw())) {
			Integer i = employeeMapper.employeeDelete(employeeCommand.getEmpId());
			model.addAttribute("num", i);
		}else {
			model.addAttribute("num" , 0);
		}
	
	}
}
