package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.BoardDAO;
import model.DTO.BoardDTO;

public class BoardListController {
	public void execute(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.selectAll();
		request.setAttribute("list", list);
		
		//new ArrayList<BoardDTO>(); // <>는 제너릴타입
	}
}
