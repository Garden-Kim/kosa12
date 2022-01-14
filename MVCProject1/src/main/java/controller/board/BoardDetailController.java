package controller.board;

import javax.servlet.http.HttpServletRequest;

import model.DAO.BoardDAO;
import model.DTO.BoardDTO;

public class BoardDetailController {
	public void execute(HttpServletRequest request) {
			String num = request.getParameter("num");
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.selectOne(num);  //BoardDTO dto = dto;
			//DTO하나를 받아와야한다 다우로부터 num을 넘기면 
			request.setAttribute("dto", dto);
	}
}
