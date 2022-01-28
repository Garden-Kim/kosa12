package springBootTest2.service.emplib;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmpLibDTO;
import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmpLibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmpLibDeleteService {
	@Autowired
	EmpLibMapper empLibMapper;
	@Autowired
	EmployeeMapper employeeMapper;

	public String execute(String num, String pw, Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		EmpLibDTO dto = empLibMapper.selectOne(num);
		String empNum = dto.getEmpNum();
		EmployeeDTO dto1 = employeeMapper.selectOne(empNum);
		model.addAttribute("dto", dto);
		System.out.println("het libNum = " + dto.getLibNum());
		String path = null;
		if (!dto.getLibPw().equals(pw)) {
			model.addAttribute("err_pw", "비밀번호틀림");
			path = "thymeleaf/emplib/emplibInfo";
		} else if (!authInfo.getUserId().equals(dto1.getEmpId())) {
			model.addAttribute("err_pw", "아이디틀림");
			path = "thymeleaf/emplib/emplibInfo";
		} else {
			Integer i = empLibMapper.emplibDelte(num);
			if (i > 0) {
				String storeFileNames[] = dto.getStoreFileName().split("`");
				String fileDir = session.getServletContext().getRealPath("/view/empLib");
				for (String fileName : storeFileNames) {
					File file = new File(fileDir + "/" + fileName);
					if (file.exists())
						file.delete();
				}
			}
			path = "redirect:emplibList";
		}
		return path;
	}
}