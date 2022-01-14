package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberDscriptController {

	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String memId = authInfo.getUserId();
		System.out.println(memId);
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.selectUser(memId);
		request.setAttribute("memberDTO", dto);
	}

}
