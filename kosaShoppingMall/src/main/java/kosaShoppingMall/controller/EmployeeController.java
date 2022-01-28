package kosaShoppingMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.service.Employee.EmployeeDeleteService;
import kosaShoppingMall.service.Employee.EmployeeDetailService;
import kosaShoppingMall.service.Employee.EmployeeListService;
import kosaShoppingMall.service.Employee.EmployeeModifyService;
import kosaShoppingMall.service.Employee.EmployeePwChangeOkService;
import kosaShoppingMall.service.Employee.EmployeePwChangeService;
import kosaShoppingMall.service.Employee.EmployeeUpdateService;
import kosaShoppingMall.service.Employee.EmployeesWriteService;


@Controller
@RequestMapping("emp")
public class EmployeeController {
	@Autowired
	EmployeesWriteService employeesWriteService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeDetailService employeeDetailService;
	@Autowired
	EmployeeModifyService employeeModifyService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	@Autowired
	EmployeePwChangeService  employeePwChangeService;
	@Autowired
	EmployeePwChangeOkService employeePwChangeOkService;
	
	//커맨드가 필요한 곳에 알아서 해주겠다?
	@ModelAttribute
	EmployeeCommand seteEmployeeCommand() {
		return new EmployeeCommand();
	}
	
	
	@RequestMapping("empPwChangeOk")
	public String empPwChangeOk(EmployeeCommand employeeCommand , Model model) {
		String path = employeePwChangeOkService.execute(employeeCommand , model);
		return path;
	}
	
	@RequestMapping("empPwChange")
	public String empPwChange(EmployeeCommand employeeCommand , Model model) {
		String path = employeePwChangeService.execute(employeeCommand , model);
		return path;
	}
	
	@RequestMapping("empDelete")
	public String empDelete(@RequestParam("empId") String empId,@RequestParam("empPw") String empPw ,EmployeeCommand employeeCommand , Model model) {
		employeeDeleteService.execute(empId,empPw,employeeCommand , model);
		return "thymeleaf/emp/empdel";
	}
	
	
	@RequestMapping(value="empUpdate" ,method = RequestMethod.POST)//BindingResult 는 커멘드에 저장이 되어서 저장이 된다.
	public String empUpdate(EmployeeCommand employeeCommand , BindingResult result ) {
		String path = employeeUpdateService.execute(employeeCommand ,result );
		return path;
	}
	
	@RequestMapping("empModify")
	public String empModify(@RequestParam(value="empId")String empId , 
							@RequestParam(value="empPw") String empPw  ,Model model) {
		System.out.println("aaaa" + empId);
		System.out.println("aaaa" + empPw);
	String 	path = employeeModifyService.execute(empId , empPw , model);
		return path;
	}
	
	@RequestMapping("empDetail")
	public String  empDetail(EmployeeCommand employeeCommand , Model model) {
		employeeDetailService.execute(employeeCommand , model);
		return "thymeleaf/emp/empDetail";	
		}
	
	
	
	@RequestMapping(value="empWrite" , method =RequestMethod.POST )//커맨드 다음에 바인딩 리절트가 있어야 한다.
	public String empWritel(@Validated  EmployeeCommand employeeCommand , BindingResult result) {
		if(!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			return "thymeleaf/emp/empForm";
		}
		employeesWriteService.execute(employeeCommand);
		return "redirect:/";
	}
	
	
	@RequestMapping(value="empWrite" , method = RequestMethod.GET)
	public String empWrite(EmployeeCommand employeeCommand) {
		return  "thymeleaf/emp/empForm";
	}
	
	@RequestMapping("empJoin")
	public String empJoin( ){
		//public String empJoin(Model model){ 이렇게 적어준것이랑 같다.
		//model.addAttribute("employeeCommand" ,new EmployeeCommand());
		return "thymeleaf/emp/empForm";
	}
	
	@RequestMapping("empList")
	public String empList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/emp/empList";
	}
	
	

}
