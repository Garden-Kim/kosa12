package model.DAO;

import java.sql.SQLException;

import model.DTO.AuthInfo;

public class LoginDAO extends DataBaseInfo{

	public AuthInfo loginCk(String id, String pw) {
		AuthInfo authInfo = null;
		con = getConnection();
		String sql = "select emp_id, emp_pw, 'emp' grade from employees "
				+ " where emp_id = ?"
				+ " UNION "
				+ " select mem_id, mem_pw, 'mem' from member "
				+ " where mem_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) { // 아이디가 존재하면 AuthInfo객체 생성
				authInfo = new AuthInfo();
				authInfo.setGrade(rs.getString("grade"));
				authInfo.setUserId(rs.getString("emp_id"));
				authInfo.setUserPw(rs.getString("emp_pw"));
			}// AuthInfo객체는 null
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return authInfo;
	}
	
}
