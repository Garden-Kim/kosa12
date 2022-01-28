package kosaShoppingMall.service.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeDetailService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmployeeCommand employeeCommand ,Model model) {
		EmployeeDTO dto =
				employeeMapper.employeeSelectOne(employeeCommand.getEmpId());
		model.addAttribute("employeeCommand" ,dto);
		
	}

}
