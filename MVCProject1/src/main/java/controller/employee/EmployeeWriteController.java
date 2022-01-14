package controller.employee;


import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;
import model.DTO.EmployeeDTO;

public class EmployeeWriteController {
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Integer empNum = Integer.parseInt(request.getParameter("empNum"));
		String empName = request.getParameter("empName");
		String empId = request.getParameter("empId");
		String empPw = request.getParameter("empPw");
		String empEmail = request.getParameter("empEmail");
		Integer empSalary = Integer.parseInt(request.getParameter("empSalary"));
		String empPhone = request.getParameter("empPhone");
		
		String empHireDate = request.getParameter("empHireDate");
		// 문자열을 날짜 변환
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dt.parse(empHireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		EmployeeDTO dto = new EmployeeDTO();
	      dto.setEmpEmail(empEmail);
	      dto.setEmpHireDate(date);
	      dto.setEmpId(empId);
	      dto.setEmpName(empName);
	      dto.setEmpNum(empNum);
	      dto.setEmpPhone(empPhone);
	      dto.setEmpPw(empPw);
	      dto.setEmpSalary(empSalary);
	      
	      EmployeeDAO dao = new EmployeeDAO();
	      dao.employeeInsert(dto);
	}
}
