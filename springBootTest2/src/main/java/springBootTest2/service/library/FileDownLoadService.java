package springBootTest2.service.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Component
@Service
public class FileDownLoadService {

	public void fileDownLoad(String sfileName, String ofileName, HttpServletRequest request,
			HttpServletResponse response) {
		String path = "/view/lib";
		String RealPath = request.getServletContext().getRealPath(path);
		String originalFileName = null;
		
		try {
			originalFileName = URLEncoder.encode(ofileName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename =" + originalFileName + ";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		File file = new File(RealPath + "/" + sfileName);
		ServletOutputStream out1 = null;
		FileInputStream fis = null;
		
		try {
			out1 = response.getOutputStream();
			fis = new FileInputStream(file);
			
			FileCopyUtils.copy(fis, out1);
			out1.flush();
			out1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
