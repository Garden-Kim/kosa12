package kosaShoppingMall.service.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder; //BindingResult는 커맨드에다가 에러메세지를 저장하는 것이다.
	public String execute(EmployeeCommand employeeCommand, BindingResult result) {
		EmployeeDTO passwordConDTO=  employeeMapper.employeeSelectOne(employeeCommand.getEmpId()); 
		String path= "redirect:empDetail?empId="+employeeCommand.getEmpId();
		String empPw = passwordEncoder.encode(employeeCommand.getEmpPw());
		EmployeeDTO dto = new EmployeeDTO();
		if(passwordEncoder.matches(employeeCommand.getEmpPw(), passwordConDTO.getEmpPw())) {
			
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpPw(empPw);
			Integer i = employeeMapper.employeeUpdate(dto);
		}else{
						// 	필드명       , 커맨드.필드명				, 출력할 메세지 
			result.rejectValue("empPw", "employeeCommand.empPw"  , "비밀번호가 틀렸습니다.");
		//	model.addAttribute("err_pw" , "비밀번호가 틀렸습니다.");
			
			path="thymeleaf/emp/empUpdate";
		}
		
		return path;
	}
		
}
