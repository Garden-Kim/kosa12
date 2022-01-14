package controller.board;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardPromtController extends HttpServlet implements Servlet{
	public void doProcess(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException{
		String rquestURI =request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = rquestURI.substring(contextPath.length());
		if(command.equals("/boardList.kosa")) {
			BoardListController action = new BoardListController();
			action.execute(request);
			// db의 값을 전달하기 위해서 request를 썻다.
			//request.setAttrubute()
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/board/boardlist.jsp");
			dispatchar.forward(request, response);
		}else if(command.equals("/boardwrite.kosa")) {
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/board/boardForm.jsp");
			dispatchar.forward(request, response);
		}else if(command.equals("/boardRegist.kosa")) {
			BoardWriteContoller action = new BoardWriteContoller();
			action.execute(request);
			System.out.println(request.getParameter("boardWriter"));
			response.sendRedirect("boardList.kosa"); 
//    /를 붙이면contextPath주소를 빼고 하는것이라서  /를 빼야한다.
		}else if(command.equals("/boardDetail.kosa")) {
			BoardVisitCountController visit = new BoardVisitCountController();
			visit.execute(request);
			BoardDetailController action = new BoardDetailController();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/board/boardInfo.jsp");
			dispatcher.forward(request, response);
		}else if (command.equals("/boardDel.kosa")) {
			BoardDelController action = new BoardDelController();
			action.execute(request);
			response.sendRedirect("boardList.kosa");
		}else if(command.equals("/boardUdate.kosa")) {
			BoardDetailController action = new BoardDetailController();
			action.execute(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/board/boardModifyForm.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/boardModify.kosa")) {
			BoardModifyController action = new BoardModifyController();
			action.execute(request);
			response.sendRedirect("boardDetail.kosa?num="+
					request.getParameter("boardNum"));
		}
		
	} 
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
