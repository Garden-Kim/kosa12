package controller.member;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.DAO.MemberDAO;
import model.DTO.MemberDTO;

public class MemberModifyController {

	public void execute(HttpServletRequest request) {
		
		String memNum = request.getParameter("memNum");
		String memName = request.getParameter("memName");
		String memId = request.getParameter("memId");
		String memPhone1 = request.getParameter("memPhone1");
		String memPhone2 = request.getParameter("memPhone2");
		String memAddr = request.getParameter("memAddr");
		String memEmail = request.getParameter("memEmail");
		String memGender = request.getParameter("memGender");
		
		
		String memBirth = request.getParameter("memBirth");
		String memRegiDate = request.getParameter("memRegiDate");
		// 문자열을 date로 변경
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date regidate = null;
		try {
			regidate = sdf.parse(memRegiDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 문자열을 Date로 변경한 후에 Timestamp로 변경
		String [] dateTime = memBirth.split("T");
		
		Date memBirthDay = null;
		try {
			memBirthDay = sdf.parse(dateTime[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Timestamp birthDay = new Timestamp(memBirthDay.getTime());
		
		MemberDTO dto = new MemberDTO();
		dto.setMemAddr(memAddr);
		dto.setMemBirth(birthDay);
		dto.setMemEmail(memEmail);
		dto.setMemGender(memGender);
		dto.setMemId(memId);
		dto.setMemName(memName);
		dto.setMemNum(memNum);
		dto.setMemPhone1(memPhone1);
		dto.setMemPhone2(memPhone2);
		dto.setMemRegiDate(regidate);
		
		MemberDAO dao = new MemberDAO();
		dao.memberUpdate(dto);
		
	}
	

}
