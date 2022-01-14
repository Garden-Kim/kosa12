package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBaseInfo {
	   String jdbcDriver;
	   String jdbcUrl;
	   Connection con;
	   PreparedStatement pstmt;
	   ResultSet rs;
	 public DataBaseInfo() {
	      jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	      jdbcUrl ="jdbc:oracle:thin:@localhost:1521:xe";
	   
	   }
	   
	   public Connection getConnection() {
	      Connection conn = null;
	      try {
	      Class.forName(jdbcDriver);
	      conn = DriverManager.getConnection(jdbcUrl,"kosa12","oracle");
	      }catch (Exception e) {
			e.printStackTrace();
		}
	      return conn;
	   }
	   
	   public void close() {
		 if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
		 if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			 }
	   }
}
