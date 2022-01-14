package controller.board;

import javax.servlet.http.HttpServletRequest;

import model.DAO.BoardDAO;
import model.DTO.BoardDTO;

public class BoardWriteContoller {
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		}catch(Exception e) {}
		String boardWrite = request.getParameter("boardWriter");
		String boardSubject = request.getParameter("boardSubject");
		String boardContent = request.getParameter("boardContent");
		String writerIP = request.getRemoteAddr();
		
		BoardDTO dto = new BoardDTO();
		dto.setBoardWrite(boardWrite);
		dto.setBoardSubject(boardSubject);
		dto.setBoardContent(boardContent);
		dto.setWriterIp(writerIP); //우리가 값을 가져오는건(입력해논건)내용, 제목 , 글쓴이 밖에 없다.그리고
		//
		
		BoardDAO dao = new BoardDAO();
		dao.boardInsert(dto);
		
		
	}
}
