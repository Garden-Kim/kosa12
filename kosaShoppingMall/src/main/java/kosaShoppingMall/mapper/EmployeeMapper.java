package kosaShoppingMall.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.EmployeeDTO;

@Component
@Repository(value="kosaShoppingMall.mapper.EmployeeMapper")
public interface EmployeeMapper {
	public Integer employeeInsert(EmployeeDTO dto);
	public List<EmployeeDTO> employeeSelectAll();
	public EmployeeDTO employeeSelectOne(String empId);
	public Integer employeeUpdate(EmployeeDTO dto);
	public Integer employeeDelete(String empId);
	public Integer employeePwChageOk(EmployeeDTO dto);
}
