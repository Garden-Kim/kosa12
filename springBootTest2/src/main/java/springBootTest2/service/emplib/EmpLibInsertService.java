package springBootTest2.service.emplib;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.EmpLibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmpLibDTO;
import springBootTest2.mapper.EmpLibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmpLibInsertService {
	@Autowired
	EmpLibMapper empLibMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmpLibCommand empLibCommand, HttpServletRequest request) {
		EmpLibDTO dto = new EmpLibDTO();
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		dto.setEmpNum(employeeMapper.empNumSelect(authInfo.getUserId()));
		dto.setIpAddr(request.getRemoteAddr());
		dto.setLibContent(empLibCommand.getLibContent());
		dto.setLibPw(empLibCommand.getLibPw());
		dto.setLibSubject(empLibCommand.getLibSubject());
		dto.setLibWriter(empLibCommand.getLibWriter());
		
		// 파일 정보를 입력하기 위한 변수 
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal ="";
		String path = "/view/empLib";
		String fileDir = request.getServletContext().getRealPath(path);
		// String fileDir="C:\Users\A-13\eclipse-workspace\workspace\kosa202110\springBootTest2\src\main\webapp\view\empLib";
		System.out.println(fileDir);
		for(MultipartFile mf : empLibCommand.getReport()) {
			String original = mf.getOriginalFilename();
			String extension = 
					original.substring(original.lastIndexOf("."));
			// 중복 파일명이 있을 때 를 대비하여 유일한 이름의 파일명을 생성
			String store = UUID.randomUUID().toString().replace("-", "");
			String storeFileName = store+extension;
			File file = new File(fileDir + "/" + storeFileName);
			String fileSize = Long.toString(mf.getSize());
			try {
				mf.transferTo(file);
			}catch(Exception e) {e.printStackTrace();}
			originalTotal += original +"`";
			storeTotal += storeFileName +"`";
			fileSizeTotal += fileSize + "`";
		}
		dto.setOriginalFileName(originalTotal);
		dto.setStoreFileName(storeTotal);
		dto.setFileSize(fileSizeTotal);
		
		
		Integer i = empLibMapper.empLibInsert(dto);
		System.out.println(i + "개 행이 삽입되었습니다.");
	}

}
