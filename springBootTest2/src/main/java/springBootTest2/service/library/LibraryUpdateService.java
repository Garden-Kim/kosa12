package springBootTest2.service.library;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.LibraryCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.LibraryDTO;
import springBootTest2.mapper.LibraryMapper;

@Component
@Service
public class LibraryUpdateService {
	@Autowired
	LibraryMapper libraryMapper;

	public String execute(LibraryCommand libraryCommand, HttpSession session, Model model) {
		String path = "redirect:libInfo?num=" + libraryCommand.getLibNum();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		LibraryDTO dto = libraryMapper.selectOne(libraryCommand.getLibNum());

		String[] fileNames = null;

		String filePath = "/view/lib";
		String fileDir = session.getServletContext().getRealPath(filePath);

		if (!dto.getLibPw().equals(libraryCommand.getLibPw()) || !dto.getMemId().equals(authInfo.getUserId())) {
			// selectOne으로 가져온 내용을 전달하면 이전 내용이 적용이 되므로
			// 수정한 내용을 수정 페이지에 전달하기 위해 libraryCommand를 전달
			model.addAttribute("dto", libraryCommand);
			model.addAttribute("err_pw", "비밀번호가 틀리거나 작성자가 아닙니다.");
			path = "thymeleaf/lib/libModify";
		} else {
			dto.setLibContent(libraryCommand.getLibContent());
			dto.setLibNum(Integer.parseInt(libraryCommand.getLibNum()));
			dto.setLibSubject(libraryCommand.getLibSubject());
			dto.setLibWriter(libraryCommand.getLibWriter());
			
			String originalTotal = "";
			String storeTotal = "";
			String fileSizeTotal = "";
			if(dto.getFileSize()!= null) {
				fileNames = dto.getStoreFileName().split("`");
				originalTotal = dto.getOriginalFileName();
				storeTotal = dto.getStoreFileName();
				fileSizeTotal = dto.getFileSize();
			}
			//////
			if (!libraryCommand.getReport()[0].getOriginalFilename().isEmpty()) {
				System.out.println(libraryCommand.getReport()[0].getOriginalFilename());
				originalTotal = "";
				storeTotal = "";
				fileSizeTotal = "";
				for (MultipartFile mf : libraryCommand.getReport()) {
					System.out.println(mf.getOriginalFilename());
					String originalFile = mf.getOriginalFilename();
					String extension = originalFile.substring(originalFile.lastIndexOf("."));
					String storeName = UUID.randomUUID().toString().replace("-", "");
					String storeFileName = storeName + extension;
					String fileSize = Long.toString(mf.getSize());

					File file = null;

					file = new File(fileDir + "/" + storeFileName);

					try {
						mf.transferTo(file);
					} catch (IllegalStateException | IOException e) {}
					originalTotal += originalFile + "`";
					storeTotal += storeFileName + "`";
					fileSizeTotal += fileSize + "`";

				}
			}
			///

			dto.setFileSize(fileSizeTotal);
			dto.setOriginalFileName(originalTotal);
			dto.setStoreFileName(fileSizeTotal);
			Integer i = libraryMapper.libraryUpdate(dto);
			if (!libraryCommand.getReport()[0].getOriginalFilename().isEmpty()) {
				if (i > 0) {
					File file = null;
					try {
						for (String fileName : fileNames) {
							file = new File(fileDir + "/" + fileName);
							if (file.exists())
								file.delete();
						}
					} catch (Exception e) {

					}
				}
			}
		}
		return path;
	}
}