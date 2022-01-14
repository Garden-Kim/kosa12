package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.BoardDTO;

public class BoardDAO extends DataBaseInfo{
	String jdbcDriver;
	String jdbcUrl;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;  // 리스트의 전체를 가져오는 것 //셀렉트하면 레코드
	
	public BoardDAO() { //BoardDAO가 될떄마다 초기화 시키겠다
	      jdbcDriver="oracle.jdbc.driver.OracleDriver"; 
	      // 여기 자료파이렝는oracle.jdbc.driver이라는 폴더가 존재하고 압축파일을 폴면 OracleDriver.class파일이 있는데
	      // 그 파일을 내가 사용하겠다는 이야기이다.
	      jdbcUrl ="jdbc:oracle:thin:@localhost:1521:xe";
	      // Url주소를 적은것이다.
	   }
	   public Connection getConnection() {
	      Connection conn = null;
	   try {
	      
	   
	      Class.forName(jdbcDriver); // 오라클 드라이버를 사용할수 있는지 적어주는것 있는지 없는지
	      conn = DriverManager.getConnection(jdbcUrl , "kosa12","oracle");
	      }catch(Exception e) {e.printStackTrace();}
	      return conn;
	   }    

	public void visitCount(String num) {
		con = getConnection();
		String sql ="update board"
				+" set visit_count = visit_count +1"
				+"  where board_num = ?";
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, num);
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이(가)추가 되었습니다.");
		}catch(Exception e) {e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(Exception e) {}
			if(con != null) try {con.close();}catch(Exception e) {}
		}
		
	}
	public void boardUpdate(BoardDTO dto){
		con = getConnection();
		String sql = "update board"
				+" set Board_writer = ? , BOARD_SUBJECT = ?,"
				+"  BOARD_CONTENT = ?"
				+" where board_num =?";
	 try {
		 
	 
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getBoardWrite());
		pstmt.setString(2, dto.getBoardSubject());
		pstmt.setString(3, dto.getBoardContent());
		pstmt.setInt(4, dto.getBoardNum());
		int i = pstmt.executeUpdate();
		System.out.println(i + "개의 행(가)삽입 되었습니다.");
	 }catch(Exception e) {e.printStackTrace();
	 }finally {
		 if(pstmt != null) try{pstmt.close();}catch(Exception e) {}
		 if(con != null) try{con.close();}catch(Exception e) {}
		
	 }
	}
	// public void boardDel(String num) num을  	pstmt.setString(1, num);여기로 준거다
	public void boardDel(String num) {
		con = getConnection();
		String sql = "delete from board where board_num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			int i = pstmt.executeUpdate();
			System.out.println(i +" 개 행이(가) 삭제되었습니다.");
		} catch (Exception e) {e.printStackTrace();}
			
		finally {
		if(pstmt != null)  try {pstmt.close();}catch(Exception e) {}
		if(con != null)  try {con.close();}catch(Exception e) {}
		}
	}
	public BoardDTO selectOne(String num) {
		BoardDTO dto = new BoardDTO();
		con = getConnection();
		String sql = "select BOARD_NUM , BOARD_WRITER,"
				+ " BOARD_SUBJECT,BOARD_CONTENT ,WRITER_IP,VISIT_COUNT "
				+ " from board"	
				+ " where BOARD_NUM = ?";
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setBoardContent(rs.getString("BOARD_CONTENT")); //rs.getstring(컬럼명을 적는것이다)
				dto.setBoardNum(rs.getInt("BOARD_NUM"));
				dto.setBoardSubject(rs.getString("BOARD_SUBJECT"));
				dto.setBoardWrite(rs.getString("BOARD_WRITER"));
				dto.setVisitCount(rs.getInt("VISIT_COUNT"));
				dto.setWriterIp(rs.getString("WRITER_IP"));
			}
			System.out.println(dto.getBoardContent());
		}catch(Exception e) {e.printStackTrace();
		}finally {
			if(rs != null) try{rs.close();}catch(Exception e) {}
			if(pstmt != null) try{pstmt.close();}catch(Exception e) {}
			if(con != null) try{con.close();}catch(Exception e) {}
		}
		
		return dto;
	}
	public List<BoardDTO> selectAll(){ //dto 하나가 하나의 행이다. list는 배열과 같지만 크기를 정하지 않고 저장가능
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		con = getConnection();
		String sql = "select BOARD_NUM , BOARD_WRITER,"
				+ " BOARD_SUBJECT,BOARD_CONTENT ,WRITER_IP,VISIT_COUNT from board"
				+ " order by board_num desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();  //리스트 전부의 값을 rs에다 넣고 
							// rs.next()할 때마다 커서가 한 행씩 이동한다.
			while(rs.next()) { //next 는 한 행을 내리는거고 eof가 만나면 flase아니면 true니
								//EOF를 만나면 더이상 반복하지를 않는다.
				BoardDTO dto = new BoardDTO();
				dto.setBoardContent(rs.getString("BOARD_CONTENT")); //rs.getstring(컬럼명을 적는것이다)
				dto.setBoardNum(rs.getInt("BOARD_NUM"));
				dto.setBoardSubject(rs.getString("BOARD_SUBJECT"));
				dto.setBoardWrite(rs.getString("BOARD_WRITER"));
				dto.setVisitCount(rs.getInt("VISIT_COUNT"));
				dto.setWriterIp(rs.getString("WRITER_IP"));
				list.add(dto);
			}
			System.out.println("인출된 모든 행 :" + list.size());
		}catch(Exception e) {e.printStackTrace();
		}finally {
			if(rs != null) try{rs.close();}catch(Exception e) {}
			if(pstmt != null) try{pstmt.close();}catch(Exception e) {}
			if(con != null) try{con.close();}catch(Exception e) {}
		}
		
		return list;
	}
	
	public void boardInsert(BoardDTO dto) {
		 // BoardWriteContoller에있는 것을 가져옴
		con = getConnection();
		String sql = " insert into board(BOARD_NUM , BOARD_WRITER,"
				+ " BOARD_SUBJECT,BOARD_CONTENT ,WRITER_IP)"
				+ " values((select nvl (max(board_num),0) +1 from board)"
				+" ,?,?,?,?)";
	try {
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dto.getBoardWrite());
		pstmt.setString(2, dto.getBoardSubject());
		pstmt.setString(3, dto.getBoardContent());
		pstmt.setString(4, dto.getWriterIp());
		int i = pstmt.executeUpdate();
		System.out.println(i + "개 행이(가) 삽입되었습니다.");
		} catch (SQLException e) {e.printStackTrace();}
		 
		finally {
			if(pstmt !=null)try{pstmt.close();}catch(Exception e){};
			if(con !=null)try {con.close();}catch(Exception e) {};
			
		}
	}
}
