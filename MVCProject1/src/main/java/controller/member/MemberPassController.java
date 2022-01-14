package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberPassController {

	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String path = null;
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String memPw = request.getParameter("memPw");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.selectUser(authInfo.getUserId());
		
		if(!memPw.equals(dto.getMemPw())) {
			request.setAttribute("msg", "비밀번호가 맞지 않습니다.");
			path = "/member/memberPass.jsp";
		}else {
			path = "/member/memberPassCon.jsp";
		}
		
		return path;
		
	}

}
