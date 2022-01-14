package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.EmployeeDTO;

public class EmployeeDAO extends DataBaseInfo{
	final String COLUMNS = "EMP_NUM, EMP_NAME, EMP_ID, EMP_PW, EMP_HIRE_DATE, EMP_EMAIL, EMP_SALARY, EMP_PHONE";
	
	public EmployeeDTO selectOne(String num) {
		EmployeeDTO dto = new EmployeeDTO();
		con = getConnection();
		String sql= " select " + COLUMNS + " from employees " 
					+" where emp_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setEmpNum(rs.getInt("EMP_NUM"));
				dto.setEmpName(rs.getString("EMP_NAME"));
				dto.setEmpId(rs.getString(3));
				dto.setEmpPhone(rs.getString(8));
				dto.setEmpEmail(rs.getString("EMP_EMAIL"));
				dto.setEmpHireDate(new java.util.Date(rs.getDate("EMP_HIRE_DATE").getTime()));
				dto.setEmpSalary(rs.getInt("emp_salary"));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return dto;
	}
	
	public void employeeInsert(EmployeeDTO dto) {
		con = getConnection();
		String sql = "insert into employees(emp_num, emp_name,"
					+" emp_id, emp_pw, emp_hire_date, emp_email,"
					+" emp_salary, emp_phone)"
					+" values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpNum());
			pstmt.setString(2, dto.getEmpName());
			pstmt.setString(3, dto.getEmpId());
			pstmt.setString(4, dto.getEmpPw());
			// utill.Date를 sql.Date 변환
			pstmt.setDate(5, new Date(dto.getEmpHireDate().getTime()));
			pstmt.setString(6, dto.getEmpEmail());
			pstmt.setInt(7, dto.getEmpSalary());
			pstmt.setString(8, dto.getEmpPhone());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 삽입되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}

	public List<EmployeeDTO> selectAll() {
		List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		con = getConnection();
		String sql = "select "+ COLUMNS + " from employees"
				+" order by EMP_NUM desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmployeeDTO dto = new EmployeeDTO();
				dto.setEmpEmail(rs.getString("EMP_EMAIL"));
				dto.setEmpHireDate(new java.util.Date(rs.getDate("EMP_HIRE_DATE").getTime()));
				dto.setEmpId(rs.getString("EMP_ID"));
				dto.setEmpName(rs.getString("EMP_NAME"));
				dto.setEmpNum(rs.getInt("EMP_NUM"));
				dto.setEmpPhone(rs.getString("EMP_PHONE"));
				dto.setEmpPw(rs.getString("EMP_PW"));
				dto.setEmpSalary(rs.getInt("EMP_SALARY"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}

	public void empDelete(String num) {
		con = getConnection();
		String sql = "delete from employees where emp_num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			int i = pstmt.executeUpdate();
			System.out.println(i + " 개 행이(가) 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	}

	public String selectEmpNum(String empId) {
		con = getConnection();
		String empNum = null;
		String sql = "select emp_num from employees"
					+" where emp_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				empNum = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return empNum;
	}
}
   